package com.sunnyside.api.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.core.env.Environment;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@PropertySource("classpath:sunnyside.properties")
@ComponentScan(basePackages = {"com.sunnyside.api"} , excludeFilters = {
		@Filter(type = FilterType.ANNOTATION, value = EnableWebMvc.class),
		@Filter(type = FilterType.ANNOTATION, value = Controller.class),
		@Filter(type = FilterType.ANNOTATION, value = EnableSwagger2.class)})
public class RootConfig {
	
	@Autowired
	private Environment env;
	
	@Bean
	public Md5PasswordEncoder mD5Encoder() {
		return new Md5PasswordEncoder();
	}
	
	@Bean
	public String salt() {
		return env.getProperty("sunnyside.salt");
	}
}
