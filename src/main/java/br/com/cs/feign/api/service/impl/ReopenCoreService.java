package br.com.cs.feign.api.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import br.com.cs.feign.api.annotation.ReopenStartStep;
import br.com.cs.feign.api.annotation.ReopenStep;
import br.com.cs.feign.api.constant.ReopenStepEnum;
import br.com.cs.feign.api.service.InfoCustomerService;
import br.com.cs.feign.api.service.InfoStepService;
import br.com.cs.feign.api.service.ReopenProcessService;
import br.com.cs.feign.api.service.dto.ReopenStepParamDTO;
import br.com.cs.feign.api.service.dto.ReopenStepRequestDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReopenCoreService {

	private final InfoStepService infoStepService;

	private final InfoCustomerService infoCustomerService;

	private final ReopenExecuteCoreService reopenExecuteCoreService;

	private final Map<ReopenStepEnum, ReopenProcessService >  reopenMapper;

	public void execute( ReopenStepRequestDTO reopenStepDTO ) {

		log.info("Obtendo os parametros para iniciar a reabertura do EC");
		ReopenStepParamDTO param = ReopenStepParamDTO.builder()
				.merchatDTO(infoCustomerService.findMerchatReopen(reopenStepDTO))
				.reopenStepDTO(reopenStepDTO)
				.steps( infoStepService.findBeanStepsReopen(reopenStepDTO) )
				.reopenSteps( initialStep() )
				.build();
		denpendsOn( param );
		log.info("Enviando para o Core executar o fluxo da Reabertura");
		this.reopenExecuteCoreService.execute(param);
	}

	private List<ReopenStepEnum> initialStep() {
		List<Entry<ReopenStepEnum, ReopenProcessService>> beanStarts = reopenMapper.entrySet().stream().filter( b ->{
			return b.getValue().getClass().getDeclaredAnnotation( ReopenStartStep.class ) != null;
		}).collect( Collectors.toList());

		if( beanStarts.isEmpty() ) {
			log.info("Não foi localizado a o Step Inicial anotado com ReopenStartStep");
			log.info("Neste Fluxo tratar para que o não entre no fluxo para processar");
			return null;
		}

		List<ReopenStepEnum> initialSteps = new ArrayList<ReopenStepEnum>();

		beanStarts.forEach( s -> {
			initialSteps.add(s.getKey());
		});

		return initialSteps;
	}

	private void denpendsOn( ReopenStepParamDTO param ){

		log.info("Definindo as Dependencias entre STEPs");
		Map<ReopenStepEnum, List<ReopenStepEnum> > denpendsOn = new LinkedHashMap<>();

		reopenMapper.entrySet().forEach( entry -> {
			ReopenProcessService reopenBeanExecute = entry.getValue();
			ReopenStep step = reopenBeanExecute.getClass().getDeclaredAnnotation( ReopenStep.class );

			if(step != null && step.nextStep() != null ) {
				Arrays.asList(step.nextStep()).forEach( depends ->{

					List<ReopenStepEnum> depend = denpendsOn.get(depends);

					if( depend == null ) {
						depend = new ArrayList<>();
					}

					depend.add(step.step());

					denpendsOn.put(depends, depend);
				});
			}

		});

		log.info("Definida as Dependencias entre STEPs");
		param.setDenpendsOn(denpendsOn);
	}
}