package br.com.cs.feign.api.service;

import br.com.cs.feign.api.constant.ReopenStepEnum;
import br.com.cs.feign.api.service.dto.ReopenStepParamDTO;

public interface ReopenProcessService {

	void process( ReopenStepParamDTO param, ReopenStepEnum reopenStenEnum );

}
