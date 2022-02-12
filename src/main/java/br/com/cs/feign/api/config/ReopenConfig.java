package br.com.cs.feign.api.config;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.cs.feign.api.annotation.ReopenStep;
import br.com.cs.feign.api.constant.ReopenStepEnum;
import br.com.cs.feign.api.service.ReopenProcessService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class ReopenConfig {

	private final ApplicationContext applicationContext;

	private Map<ReopenStepEnum, ReopenProcessService > reopenMapper;


	@PostConstruct
	private void loadMapper() {

		this.reopenMapper = new LinkedHashMap<>();

		ObjectProvider<ReopenProcessService> beans = this.applicationContext
				.getAutowireCapableBeanFactory()
				.getBeanProvider(ReopenProcessService.class);

		beans.forEach( bean -> {
			log.info("ReopenBeans ::: " + bean.getClass().getSimpleName() );
			ReopenStep reopneStep = bean.getClass().getDeclaredAnnotation( ReopenStep.class );
			if( reopneStep != null ) {
				this.reopenMapper.put( reopneStep.step(), bean );
			}
		} );
	}



	@Bean
	public Map<ReopenStepEnum, ReopenProcessService >  reopenMapper(){
		return this.reopenMapper;
	}


}