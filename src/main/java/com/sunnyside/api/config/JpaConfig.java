package com.sunnyside.api.config;

import java.beans.PropertyVetoException;

import javax.sql.DataSource;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@Configuration
@PropertySource("classpath:sunnyside.properties")
@EnableJpaRepositories(basePackages = "com.sunnyside.api.repository")
@EnableTransactionManagement
public class JpaConfig implements InitializingBean{
	
	@Autowired
	private Environment env;

	//DataSource properties
	private String username;
	private String password;
	private String url;
	private String driverClassName;
	private int minPoolSize;
	private int maxPoolSize;
	private int maxStatements;
	private boolean testConntection;
	private boolean showSql;
	private String dialect;
	
	@Override
	public void afterPropertiesSet() throws Exception {
		username = env.getProperty("sunnyside.db.username", String.class);
		password = env.getProperty("sunnyside.db.password",  String.class);
		url = env.getProperty("sunnyside.db.url", String.class);
		driverClassName = env.getProperty("sunnyside.db.driverClassName", String.class);
		minPoolSize = env.getProperty("sunnyside.jdbc.minPoolSize", Integer.class);
		maxPoolSize = env.getProperty("sunnyside.jdbc.maxPoolSize", Integer.class);
		maxStatements = env.getProperty("sunnyside.jdbc.maxStatements", Integer.class);
		testConntection = env.getProperty("sunnyside.jdbc.testConnection", Boolean.class);
		showSql = env.getProperty("sunnyside.hibernate.showSql", Boolean.class);
		dialect = env.getProperty("sunnyside.hibernate.dialect", String.class);
	}

	//Configure the JDBC Driver
	/**
	 * Create a bean of the DataSource...
	 * Here we will be using a ComboPooledDataSource
	 * This allows a pooled connection to the database that 
	 * can be reused
	 * @throws PropertyVetoException 
	 */
	@Bean(destroyMethod = "close")
	public DataSource dataSource() throws PropertyVetoException {
		final ComboPooledDataSource dataSource = new ComboPooledDataSource();
		dataSource.setUser(username);
		dataSource.setPassword(password);
		dataSource.setJdbcUrl(url);
		dataSource.setDriverClass(driverClassName);
		dataSource.setMinPoolSize(minPoolSize);
		dataSource.setMaxPoolSize(maxPoolSize);
		dataSource.setMaxStatements(maxStatements);
		dataSource.setTestConnectionOnCheckout(testConntection);
		return dataSource;
	}
	
	@Bean
	public JpaVendorAdapter jpaVendorAdapter() {
		HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
		adapter.setDatabase(Database.MYSQL);
		adapter.setShowSql(showSql);
		adapter.setDatabasePlatform(dialect);
		return adapter;
	}
	
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() throws PropertyVetoException {
		LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
		factory.setDataSource(dataSource());
		factory.setJpaVendorAdapter(jpaVendorAdapter());
		factory.setPackagesToScan("com.sunnyside.api.entity");
		return factory;
	}
	
	@Bean 
	public PlatformTransactionManager transactionManager() {
		return new JpaTransactionManager();
	}

}
