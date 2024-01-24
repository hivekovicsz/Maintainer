package com.example.maintainer.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CommonExceptionConfig {

	@Bean
	public GlobalControllerExceptionHandler getGlobalControllerExceptionHandler() {
		return new GlobalControllerExceptionHandler();
	}
	
}