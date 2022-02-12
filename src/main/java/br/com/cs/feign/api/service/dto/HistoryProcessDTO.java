package br.com.cs.feign.api.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HistoryProcessDTO {

	private Long id;
	private String message;
	private String reopenHash;
	private String status;
	private Long reprocessed;
}
