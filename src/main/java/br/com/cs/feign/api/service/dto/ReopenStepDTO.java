package br.com.cs.feign.api.service.dto;

import br.com.cs.feign.api.constant.ReopenStepEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReopenStepDTO {
	private Long id;
	private ReopenStepEnum step;
	private String reopenHash;
	private String status;
	private Long qtdProcess;
}
