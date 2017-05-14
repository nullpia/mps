package com.nullpia.mps;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ScheduledExecutorFactoryBean;

/**
 * 
 * @author nullpia
 */
@Configuration
public class AppScheulder {

	/* Defined in application.yml */
	@Value("${app.scheduler.executor-count:5}")
	private int schedulerExecutorCount;
	
	@Bean
    public ScheduledExecutorFactoryBean scheduledExecutorFactoryBean() {
        ScheduledExecutorFactoryBean bean = new ScheduledExecutorFactoryBean();
        bean.setPoolSize(schedulerExecutorCount);
        return bean;
    }
}
