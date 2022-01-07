package com.anant.springsecurity.demo.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;

@Configuration
@EnableWebSecurity
public class DemoSecurityConfig extends WebSecurityConfigurerAdapter {

	/// add rfrence to datasource
	@Autowired
	private DataSource securitydatasource;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// add code for in memory authentication

		// use jdbc authentication

		auth.jdbcAuthentication().dataSource(securitydatasource);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests().antMatchers("/").hasRole("EMPLOYEE").antMatchers("/leaders/**").hasRole("Manager")
				.antMatchers("/systems/**").hasRole("Admin").and().formLogin().loginPage("/showMyLoginPage")
				.loginProcessingUrl("/authenticatetheuser").permitAll().and().logout().permitAll().and()
				.exceptionHandling().accessDeniedPage("/access-denied");
	}

}
