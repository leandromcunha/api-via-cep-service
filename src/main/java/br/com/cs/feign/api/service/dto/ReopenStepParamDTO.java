package br.com.cs.feign.api.service.dto;

import java.util.List;
import java.util.Map;

import br.com.cs.feign.api.constant.ReopenStepEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReopenStepParamDTO {

	public MerchatDTO merchatDTO;

	public Map<ReopenStepEnum, ReopenStepDTO > steps;

	public Map<ReopenStepEnum, List<ReopenStepEnum> > denpendsOn;

	public ReopenStepRequestDTO reopenStepDTO;

	public List<ReopenStepEnum> reopenSteps;

}
