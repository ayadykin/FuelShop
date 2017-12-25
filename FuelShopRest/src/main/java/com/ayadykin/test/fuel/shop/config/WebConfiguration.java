package com.ayadykin.test.fuel.shop.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@PropertySource(value = { "classpath:persistance.properties" })
public class WebConfiguration extends WebMvcConfigurerAdapter {

	@Bean
	public ModelMapper getModelMapper() {
		return new ModelMapper();
	}
}
