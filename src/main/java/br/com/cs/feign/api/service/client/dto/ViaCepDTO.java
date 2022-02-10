package br.com.cs.feign.api.service.client.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Tag(name="ViaCepDTO")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Schema(name = "ViaCepDTO", description = "Resposta da Via CEP da consulta por código do CEP")
public class ViaCepDTO {

	@Schema(name = "cep", description = "Número do CEP")
	private String cep;

	@Schema(name = "logradouro", description = "Nome da Rua")
	private String logradouro;

	@Schema(name = "complemento", description = "Complemento do endereço")
	private String complemento;

	@Schema(name = "bairro", description = "Bairro")
	private String bairro;

	@Schema(name = "localidade", description = "Cidade")
	private String localidade;

	@Schema(name = "uf", description = "Sigla do Estado (UF)")
	private String uf;

	@Schema(name = "ibge", description = "Código do IBGE - Instituto Brasileiro de Geografia e Estatística")
	private String ibge;

	@Schema(name = "gia", description = "Código GIA - Guia de Informação e Apuração do ICMS")
	private String gia;

	@Schema(name = "ddd", description = "Código DDD da localidade")
	private String ddd;

	@Schema(name = "siafi", description = "Código SIAFI - Sistema Integrado de Administração Financeira do Governo Federal")
	private String siafi;
}