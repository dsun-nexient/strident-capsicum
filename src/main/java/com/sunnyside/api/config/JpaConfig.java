package com.sunnyside.api.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@PropertySource("classpath:sunnyside.properties")
@EnableJpaRepositories(basePackages = "com.sunnyside.api.repository")
@EnableTransactionManagement
public class JpaConfig {

}
