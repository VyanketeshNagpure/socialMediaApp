package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	private final UserAuthenticationProvider userAuthenticationProvider;
	private final UserAuthenticationEntryPoint userauthenticationEntryPoint;
	
	@Autowired
	public SecurityConfig(UserAuthenticationProvider userAuthenticationProvider,
			UserAuthenticationEntryPoint userauthenticationEntryPoint) {
		super();
		this.userAuthenticationProvider = userAuthenticationProvider;
		this.userauthenticationEntryPoint = userauthenticationEntryPoint;
	}
	
	 @Bean
	    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	        http
	                .exceptionHandling((exception) ->exception.authenticationEntryPoint(userauthenticationEntryPoint))
	                //.and()
	                .addFilterBefore(new UsernamePassAuthFilter(userAuthenticationProvider), BasicAuthenticationFilter.class)
	                .addFilterBefore(new JwtAuthFilter(userAuthenticationProvider), UsernamePassAuthFilter.class)
	                .csrf(csrf -> csrf.disable())
	                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
	               // .and()
	                .authorizeHttpRequests((requests) -> requests
	                        .requestMatchers(HttpMethod.POST, "/login", "/signUp").permitAll()
	                        .anyRequest().permitAll())
	                ;
	        
	        return http.build();
	    }

}
