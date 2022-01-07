package com.anant.springsecurity.demo.config;

import java.beans.PropertyVetoException;
import java.util.logging.Logger;

import javax.sql.DataSource;

import org.springframework.aop.TargetClassAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.anant.springsecurity.demo")
@PropertySource("classpath:persistence-mysql.properties")
public class DemoAppConfig {

	@Autowired
	private Environment env;

	private Logger logger = Logger.getLogger(getClass().getName());

	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver ivnr = new InternalResourceViewResolver();

		ivnr.setPrefix("/WEB-INF/view/");
		ivnr.setSuffix(".jsp");
		return ivnr;
	}

	@Bean
	public DataSource securityDataSource() {
		ComboPooledDataSource securitydatasource = new ComboPooledDataSource();

		try {
			securitydatasource.setDriverClass(env.getProperty("jdbc.driver"));

		} catch (PropertyVetoException e) {
			throw new RuntimeException(e);
		}

		logger.info("jdbc url" + env.getProperty("jdbc.url"));
		logger.info("jdbc user" + env.getProperty("jdbc.user"));

		// set database properties
		securitydatasource.setJdbcUrl(env.getProperty("jdbc.url"));
		securitydatasource.setUser(env.getProperty("jdbc.user"));
		securitydatasource.setPassword(env.getProperty("jdbc.password"));

		securitydatasource.setInitialPoolSize(getProperty("connection.pool.initialPoolSize"));

		securitydatasource.setMinPoolSize(getProperty("connection.pool.minPoolSize"));

		securitydatasource.setMaxPoolSize(getProperty("connection.pool.maxPoolSize"));

		securitydatasource.setMaxIdleTime(getProperty("connection.pool.maxIdleTime"));

		return securitydatasource;
	}

	private int getProperty(String propertyName) {
		String propVal = env.getProperty(propertyName);
		int intPropValue = Integer.parseInt(propVal);

		return intPropValue;

	}
}
