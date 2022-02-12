package br.com.cs.feign.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.cs.feign.api.controller.openapi.ZipOpenApi;
import br.com.cs.feign.api.service.client.ViaCepClient;
import br.com.cs.feign.api.service.client.dto.ViaCepDTO;
import br.com.cs.feign.api.service.dto.ReopenStepRequestDTO;
import br.com.cs.feign.api.service.impl.ReopenCoreService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/api/via-cep")
@RequiredArgsConstructor
public class ZipController implements ZipOpenApi {


	private final ViaCepClient viaCepClient;

	private final ReopenCoreService reopenExecuteCoreService;

	@Override
	@GetMapping("/v1/ceps/{cep}")
	public ResponseEntity<ViaCepDTO> consultarCEP( @PathVariable("cep") String cep ) {

		log.info("Ler essa informação da fila:: Consumer");
		ReopenStepRequestDTO reopenStepDTO = new ReopenStepRequestDTO();
		reopenExecuteCoreService.execute( reopenStepDTO );

		ViaCepDTO response = viaCepClient.buscarCEP(cep);

		return ResponseEntity
				.status( HttpStatus.OK )
				.body( response );
	}
}
