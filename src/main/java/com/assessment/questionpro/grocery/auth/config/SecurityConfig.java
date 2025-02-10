package com.assessment.questionpro.grocery.auth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.assessment.questionpro.grocery.auth.service.UserService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	private final UserService userService;
	private final PasswordEncoder passwordEncoder;

	public SecurityConfig(UserService userService, PasswordEncoder passwordEncoder) {
		this.userService = userService;
		this.passwordEncoder = passwordEncoder;
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.csrf().disable()
				.authorizeHttpRequests(authz -> authz.requestMatchers("/admin/**")//
						.hasRole("ADMIN")
						.requestMatchers("user/**")//
						.hasRole("USER")//
						.requestMatchers("/auth/register", "/auth/login")
						.permitAll().anyRequest().authenticated())
				// Custom login configuration
				.formLogin(form -> form.loginPage("/auth/login") // Specify the custom login page URL
						.loginProcessingUrl("/auth/login") // Specify the URL to handle the login form submission
						.usernameParameter("username") // The username field in the login form
						.passwordParameter("password") // The password field in the login form
						.permitAll())
				// Basic Authentication configuration using the basic() method
				.httpBasic();

		return http.build();
	}

	@Bean
	public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
		return http.getSharedObject(AuthenticationManagerBuilder.class).userDetailsService(userService)
				.passwordEncoder(passwordEncoder).and().build();
	}

}