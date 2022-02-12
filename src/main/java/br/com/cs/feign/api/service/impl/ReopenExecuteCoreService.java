package br.com.cs.feign.api.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import br.com.cs.feign.api.annotation.ReopenStep;
import br.com.cs.feign.api.constant.ReopenStepEnum;
import br.com.cs.feign.api.service.ReopenProcessService;
import br.com.cs.feign.api.service.dto.ReopenStepDTO;
import br.com.cs.feign.api.service.dto.ReopenStepParamDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
class ReopenExecuteCoreService {

	private final Map<ReopenStepEnum, ReopenProcessService >  reopenMapper;

	public void execute( ReopenStepParamDTO param ) {
		param.reopenSteps.forEach( bean -> {
			stepExecute(param, bean);
		});
	}

	private void stepExecute(ReopenStepParamDTO param, ReopenStepEnum bean) {
		ReopenProcessService reopenBeanExecute = reopenMapper.get(bean);
		if( stepNotWasExecuted( param , bean ) ) {
			log.info( String.format( "Executando o STEP %s", bean.name() ) );
			reopenBeanExecute.process(param, bean);
		}

		ReopenStep step = reopenBeanExecute.getClass().getDeclaredAnnotation( ReopenStep.class );
		if(step != null && step.nextStep() != null && dependsOnWasExecuted(param, step.step() ) ) {
			execute( params( param, step.nextStep()));
		}
	}

	private ReopenStepParamDTO params(ReopenStepParamDTO param , ReopenStepEnum...reopenStepEnum) {
		return ReopenStepParamDTO.builder()
				.merchatDTO(param.getMerchatDTO())
				.reopenStepDTO(param.getReopenStepDTO())
				.steps( param.getSteps() )
				.denpendsOn(param.getDenpendsOn())
				.reopenSteps(Arrays.asList(reopenStepEnum))
				.build();
	}

	private boolean stepNotWasExecuted(ReopenStepParamDTO param, ReopenStepEnum reopenStepEnum) {
		log.info( String.format( "Verificando se o STEP %s já foi executado.", reopenStepEnum.name()) );
		if( param.getSteps().containsKey(reopenStepEnum) ) {
			return param.getSteps().get(reopenStepEnum).getStatus().equals("N");
		}
		return true;
	}

	private boolean dependsOnWasExecuted( ReopenStepParamDTO param, ReopenStepEnum reopenStepEnum ) {
		List<ReopenStepEnum> dependsOn = param.getDenpendsOn().get(reopenStepEnum);

		if(dependsOn == null || dependsOn.isEmpty() ) {
			log.info( String.format( "STEP %s não tem dependecias pode executar", reopenStepEnum.name() ) );
			return true;
		}

		List<ReopenStepEnum> dependStatus = dependsOn.stream().filter( d -> {
			ReopenStepDTO stepStatus = param.getSteps().get(d);
			return "S".equals( stepStatus.getStatus() );
		}).collect( Collectors.toList()) ;

		return  dependStatus.size() == dependsOn.size();

	}
}