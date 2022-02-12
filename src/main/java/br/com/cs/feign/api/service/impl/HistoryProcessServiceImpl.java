package br.com.cs.feign.api.service.impl;

import org.springframework.stereotype.Service;

import br.com.cs.feign.api.service.HistoryProcessService;
import br.com.cs.feign.api.service.dto.HistoryProcessDTO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class HistoryProcessServiceImpl implements HistoryProcessService {
	@Override
	public void save(HistoryProcessDTO historyProcessDTO) {
		log.info("Implentar a chamda do serviço que grava o histórico");
	}
}