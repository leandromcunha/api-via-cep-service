package br.com.cs.feign.api.config;

import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@Configuration
@OpenAPIDefinition(info = @Info(
    title = "Leandro Marques da Cunha - Consulta CEP",
    version = "1.0.0",
    description = "Consulta de CEP"
))
public class SwaggerConfig {


}

