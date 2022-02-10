package br.com.cs.feign.api.controller.openapi;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import br.com.cs.feign.api.service.client.dto.ViaCepDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;


@Tag(name = "ZipController", description = "Consultar CEP")
public interface ZipOpenApi {

	@Operation(summary = "Consultar CEP", description = "Consulta os dados dos CEP no serviço via cep")
	public ResponseEntity<ViaCepDTO>  consultarCEP( @PathVariable("cep") String cep );

}
