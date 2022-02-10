package br.com.cs.feign.api.controller;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.cs.feign.api.annotation.ReopenStep;
import br.com.cs.feign.api.constant.ReopenStenEnum;
import br.com.cs.feign.api.controller.openapi.ZipOpenApi;
import br.com.cs.feign.api.service.ReopenProcessServece;
import br.com.cs.feign.api.service.client.ViaCepClient;
import br.com.cs.feign.api.service.client.dto.ViaCepDTO;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/api/via-cep")
@RequiredArgsConstructor
public class ZipController implements ZipOpenApi {

	private final ViaCepClient viaCepClient;

	private final Map<ReopenStenEnum, ReopenProcessServece >  reopenMapper;

	@GetMapping("/v1/ceps/{cep}")
	@Override
	public ResponseEntity<ViaCepDTO> consultarCEP( @PathVariable("cep") String cep ) {

		ReopenProcessServece value = reopenMapper.get( ReopenStenEnum.REABERTURA );
		value.process("Leandro");

		ReopenStep step = value.getClass().getDeclaredAnnotation( ReopenStep.class );

		ReopenStenEnum[] nextStep = step.nextStep();

		for (ReopenStenEnum reopenStenEnum : nextStep) {
			value = reopenMapper.get( reopenStenEnum );
			value.process("Leandro");
		}

		ViaCepDTO response = viaCepClient.buscarCEP(cep);

		return ResponseEntity
				.status( HttpStatus.OK )
				.body( response );
	}
}
