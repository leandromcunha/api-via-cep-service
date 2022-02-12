package br.com.cs.feign.api.service.impl;

import org.springframework.stereotype.Service;

import br.com.cs.feign.api.annotation.ReopenStep;
import br.com.cs.feign.api.constant.ReopenStepEnum;
import br.com.cs.feign.api.service.dto.HistoryProcessDTO;
import br.com.cs.feign.api.service.dto.MerchatDTO;

@Service("UpdateInfoECImpl")
@ReopenStep(step = ReopenStepEnum.UPDATE_INFO_EC , nextStep = ReopenStepEnum.EVENTO_SEC )
class UpdateInfoECImpl extends ReopenProcessAbstract {

	@Override
	protected HistoryProcessDTO execute(MerchatDTO merchatDTO) throws Exception {
		return HistoryProcessDTO.builder().message("Sucesso na Execução").status("S").build();
	}
}
