package br.com.cs.feign.api.service.impl;

import org.springframework.stereotype.Service;

import br.com.cs.feign.api.annotation.ReopenStep;
import br.com.cs.feign.api.constant.ReopenStenEnum;
import br.com.cs.feign.api.service.ReopenProcessServece;

@Service( "OpenSecEventStepServiceImpl")
@ReopenStep(step = ReopenStenEnum.EVENTO_SEC  )
public class OpenSecEventStepServiceImpl implements ReopenProcessServece {
	@Override
	public void process(String value) {
		System.out.println( ReopenStenEnum.EVENTO_SEC.name()+"::" + value  );
	}

}
