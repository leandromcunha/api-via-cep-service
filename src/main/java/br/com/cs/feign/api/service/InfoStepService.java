package br.com.cs.feign.api.service;

import java.util.Map;

import br.com.cs.feign.api.constant.ReopenStepEnum;
import br.com.cs.feign.api.service.dto.ReopenStepDTO;
import br.com.cs.feign.api.service.dto.ReopenStepRequestDTO;

public interface InfoStepService {

	Map<ReopenStepEnum, ReopenStepDTO >  findBeanStepsReopen( ReopenStepRequestDTO reopenStepDTO );
}
