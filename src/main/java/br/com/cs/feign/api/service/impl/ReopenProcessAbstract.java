package br.com.cs.feign.api.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.cs.feign.api.constant.ReopenStepEnum;
import br.com.cs.feign.api.service.HistoryProcessService;
import br.com.cs.feign.api.service.ReopenProcessService;
import br.com.cs.feign.api.service.dto.HistoryProcessDTO;
import br.com.cs.feign.api.service.dto.MerchatDTO;
import br.com.cs.feign.api.service.dto.ReopenStepDTO;
import br.com.cs.feign.api.service.dto.ReopenStepParamDTO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
abstract class ReopenProcessAbstract implements ReopenProcessService{

	@Autowired
	protected HistoryProcessService historyProcessService;

	protected abstract HistoryProcessDTO execute(MerchatDTO merchatDTO ) throws Exception;

	@Override
	public void process( ReopenStepParamDTO param, ReopenStepEnum reopenStenEnum ) {
		log.info( "Step:: " + reopenStenEnum.name() );

		try {
			log.info( "processando o Step" );
			HistoryProcessDTO history = execute(param.getMerchatDTO());
			updateStepExecute( param, history, reopenStenEnum);
			saveHistoryStep(history);
		} catch (Exception e) {
			log.error( String.format( "Erro ao tentar processar o STEP %s" ,reopenStenEnum.name()), e );
			HistoryProcessDTO history =  HistoryProcessDTO.builder().message(e.getMessage()).status("N").build();
			saveHistoryStep(history);
		}
	}

	private void updateStepExecute(ReopenStepParamDTO param, HistoryProcessDTO history, ReopenStepEnum reopenStenEnum) {

		Map<ReopenStepEnum, ReopenStepDTO> steps = param.getSteps();

		if( !steps.containsKey(reopenStenEnum) ) {
			steps.put(reopenStenEnum, ReopenStepDTO.builder()
					.step(reopenStenEnum)
					.status("N")
					.reopenHash( param.reopenStepDTO.getHashReopen() )
					.qtdProcess(0L)
					.build() );
		}

		ReopenStepDTO reopenStepDTO = steps.get(reopenStenEnum);
		reopenStepDTO.setQtdProcess( reopenStepDTO.getQtdProcess() + 1L  );
		reopenStepDTO.setStatus(history.getStatus() );
		steps.put(reopenStenEnum, reopenStepDTO);
		history.setId( reopenStepDTO.getId() );
		history.setReprocessed( reopenStepDTO.getQtdProcess() );
		history.setReopenHash( reopenStepDTO.getReopenHash() );
	}

	private void saveHistoryStep(HistoryProcessDTO history) {
		log.info( "Gravando/Atualizando o Histórico" );
		this.historyProcessService.save(history);
	}
}
