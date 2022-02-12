package br.com.cs.feign.api.service;

import br.com.cs.feign.api.service.dto.MerchatDTO;
import br.com.cs.feign.api.service.dto.ReopenStepRequestDTO;

public interface InfoCustomerService {

	MerchatDTO findMerchatReopen( ReopenStepRequestDTO reopenStepDTO);
}
