package br.com.cs.feign.api.service.impl;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import br.com.cs.feign.api.constant.ReopenStepEnum;
import br.com.cs.feign.api.service.InfoStepService;
import br.com.cs.feign.api.service.dto.ReopenStepDTO;
import br.com.cs.feign.api.service.dto.ReopenStepRequestDTO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class InfoStepServiceImpl implements InfoStepService {

	@Override
	public Map<ReopenStepEnum, ReopenStepDTO> findBeanStepsReopen(ReopenStepRequestDTO reopenStepDTO) {
		log.info("Implementar a busca dos STEPS");

		Map<ReopenStepEnum, ReopenStepDTO > steps = new LinkedHashMap<ReopenStepEnum, ReopenStepDTO>();
//		steps.put(ReopenStepEnum.REABERTURA, ReopenStepDTO.builder().status("S").step(ReopenStepEnum.REABERTURA).qtdProcess(1l).build());
//		steps.put(ReopenStepEnum.UPDATE_INFO_DC, ReopenStepDTO.builder().status("S").step(ReopenStepEnum.UPDATE_INFO_DC).qtdProcess(1l).build());
//		steps.put(ReopenStepEnum.UPDATE_INFO_EC, ReopenStepDTO.builder().status("N").step(ReopenStepEnum.UPDATE_INFO_EC).qtdProcess(1l).build());
//		steps.put(ReopenStepEnum.EVENTO_SEC, ReopenStepDTO.builder().status("N").step(ReopenStepEnum.EVENTO_SEC).qtdProcess(1l).build());
		return steps;

	}

}
