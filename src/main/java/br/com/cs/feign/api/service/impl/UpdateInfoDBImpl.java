package br.com.cs.feign.api.service.impl;

import org.springframework.stereotype.Service;

import br.com.cs.feign.api.annotation.ReopenStep;
import br.com.cs.feign.api.constant.ReopenStenEnum;
import br.com.cs.feign.api.service.ReopenProcessServece;

@ReopenStep(step = ReopenStenEnum.UPDATE_INFO_DC  )
@Service("UpdateInfoDBImpl")
public class UpdateInfoDBImpl implements ReopenProcessServece {
	@Override
	public void process(String value) {
		System.out.println( ReopenStenEnum.UPDATE_INFO_DC.name() +"::" + value );
	}

}
