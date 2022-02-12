package br.com.cs.feign.api.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReopenStepRequestDTO {

	private Long reopenId;

	private String hashReopen;

	private String merchant;
}
