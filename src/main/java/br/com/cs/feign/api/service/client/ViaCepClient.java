package br.com.cs.feign.api.service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import br.com.cs.feign.api.service.client.dto.ViaCepDTO;

@FeignClient(name = "via-cep", url = "${app.urls.service.via-cep}")
public interface ViaCepClient {

	@GetMapping( path = "/ws/{zip-code}/json", consumes = MediaType.APPLICATION_JSON_VALUE , produces = MediaType.APPLICATION_JSON_VALUE )
	ViaCepDTO buscarCEP( @PathVariable("zip-code") String cep );
}
