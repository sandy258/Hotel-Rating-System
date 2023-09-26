package com.apigateway.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;

import com.google.common.net.HttpHeaders;


@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {
	
	@Autowired
	private RouteValidator routeValidator;

	
	public AuthenticationFilter() {
		super(Config.class);
	}
	public static class Config{
		
	}

	@Override
	public GatewayFilter apply(Config config) {
		return ((exchange,chain) ->{
			
			if(routeValidator.isSecured.test(exchange.getRequest())) {
				// lets check header whether the token is coming or not 
				if(!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
					throw new RuntimeException("Missing authorization header");
				}
				String authHeader=exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
				if(authHeader!=null && authHeader.startsWith("Bearer ")){
					authHeader=authHeader.substring(7);
				}
			}
			return chain.filter(exchange);
		});
	}

}
