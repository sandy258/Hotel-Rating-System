package com.jwt3.config;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.jwt3.security.JWTAuthenticationEntryPoint;
import com.jwt3.security.JWTAuthenticationFilter;

@Configuration
public class JWTSecurityConfig {
	
	@Autowired
	private JWTAuthenticationEntryPoint authenticationEntryPoint;
	@Autowired
	private JWTAuthenticationFilter authenticationFilter;
	
	@Autowired
	private UserDetailsService detailsService;
	
	@Autowired
	private PasswordEncoder encoder;
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity ) throws Exception {
		httpSecurity.csrf((csrf) -> csrf.disable())
		.authorizeHttpRequests((auth) ->auth
				.requestMatchers("home/**")
				.authenticated()
				.requestMatchers("auth/login")
				.permitAll()
				.requestMatchers("auth/create")
				.permitAll()
				.anyRequest()
				.authenticated())
				.exceptionHandling(ex -> ex.authenticationEntryPoint(authenticationEntryPoint))
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		httpSecurity.addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class);
		return httpSecurity.build();
	}
	
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider  authenticationProvider=new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(detailsService);
		authenticationProvider.setPasswordEncoder(encoder);
		return authenticationProvider;
	}
}
