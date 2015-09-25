package com.sunnyside.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
/*
 * Swagger must have access to the web layer beans in order to be able to produce documentation
 * Issue: The swagger configuration was being used in the root application context instead of the web application context
 */
@EnableSwagger2
public class SwaggerConfig {
	private static final String CONTACT = "";
	private static final String DESCRIPTION = "These are the Restful API calls rendered by the server";
	private static final String LICENSE = "";
	private static final String LICENSE_URL = "";
	private static final String TERMS_OF_SERVICE_URL = "";
	private static final String VERSION = "0.0.1";

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.regex("/.*")).build().pathMapping("/").apiInfo(this.apiInfo());
	}

	@Bean
	public UiConfiguration uiConfig() {
		return UiConfiguration.DEFAULT;
	}

	private ApiInfo apiInfo() {
		final ApiInfo apiInfo = new ApiInfo("e-Testing REST API", SwaggerConfig.DESCRIPTION, SwaggerConfig.VERSION,
				SwaggerConfig.TERMS_OF_SERVICE_URL, SwaggerConfig.CONTACT, SwaggerConfig.LICENSE,
				SwaggerConfig.LICENSE_URL);
		return apiInfo;
	}
}
