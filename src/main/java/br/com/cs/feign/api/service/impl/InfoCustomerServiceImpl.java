package br.com.cs.feign.api.service.impl;

import org.springframework.stereotype.Service;

import br.com.cs.feign.api.service.InfoCustomerService;
import br.com.cs.feign.api.service.dto.MerchatDTO;
import br.com.cs.feign.api.service.dto.ReopenStepRequestDTO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class InfoCustomerServiceImpl implements InfoCustomerService {

	@Override
	public MerchatDTO findMerchatReopen(ReopenStepRequestDTO reopenStepDTO) {
		log.info( "Cache desta chamada tempo x retentativas" );
		return new MerchatDTO();
	}

}
