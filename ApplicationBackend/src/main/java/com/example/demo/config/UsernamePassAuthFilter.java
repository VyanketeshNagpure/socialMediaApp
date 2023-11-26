package com.example.demo.config;

import java.io.IOException;

import org.springframework.http.HttpMethod;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.demo.dto.CredentialsDto;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class UsernamePassAuthFilter extends OncePerRequestFilter {
	
	private static final ObjectMapper MAPPER = new ObjectMapper();
	
	private final UserAuthenticationProvider provider;
	
	

	public UsernamePassAuthFilter(UserAuthenticationProvider provider) {
		super();
		this.provider = provider;
	}



	@Override
	protected void doFilterInternal(HttpServletRequest request,
									HttpServletResponse response, 
									FilterChain filterChain) throws ServletException, IOException {

		if("/login".equals(request.getServletPath())
				&& HttpMethod.POST.matches(request.getMethod())) {
			
			CredentialsDto credentialsDto = MAPPER.readValue(request.getInputStream(), CredentialsDto.class);
			
			try {
				SecurityContextHolder.getContext().setAuthentication(
						provider.validateCredentials(credentialsDto)
						);
			}
			catch (RuntimeException e) {
				SecurityContextHolder.clearContext();
				throw e;
			}
		}
		
		filterChain.doFilter(request, response);
		
		
		
	}

}
