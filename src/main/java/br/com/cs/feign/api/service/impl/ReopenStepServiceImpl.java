package br.com.cs.feign.api.service.impl;

import org.springframework.stereotype.Service;

import br.com.cs.feign.api.annotation.ReopenStep;
import br.com.cs.feign.api.constant.ReopenStenEnum;
import br.com.cs.feign.api.service.ReopenProcessServece;

@Service("ReopenStepServiceImpl")
@ReopenStep( step = ReopenStenEnum.REABERTURA , nextStep = {ReopenStenEnum.EVENTO_SEC, ReopenStenEnum.UPDATE_INFO_EC, ReopenStenEnum.UPDATE_INFO_DC } )
public class ReopenStepServiceImpl implements ReopenProcessServece {

	@Override
	public void process(String value) {
		System.out.println( ReopenStenEnum.REABERTURA.name() +"::" + value );
	}

}
