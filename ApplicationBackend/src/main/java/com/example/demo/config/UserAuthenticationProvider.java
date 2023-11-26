package com.example.demo.config;

import java.util.Base64;
import java.util.Collections;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.example.demo.dto.CredentialsDto;
import com.example.demo.dto.UserDto;
import com.example.demo.service.AuthenticationService;

import jakarta.annotation.PostConstruct;

@Component
public class UserAuthenticationProvider {
	
	@Value("${security.jwt.token.secret-key:secret_key}")
	private String secretkey;
	
	AuthenticationService authenticationService;
	
	@Autowired
	public UserAuthenticationProvider(AuthenticationService authenticationService) {
		super();
		this.authenticationService = authenticationService;
	}

	@PostConstruct
	protected void init() {
		secretkey = Base64.getEncoder().encodeToString(secretkey.getBytes());
	}
	
	
	public String createToken(String login) {
		
		Algorithm algorithm = Algorithm.HMAC256(secretkey);
		
		Date now = new Date();
		Date validity = new Date(now.getTime() + 3600000); // validity 1 hr
		
		return JWT.create()
				.withIssuer(login)
				.withIssuedAt(now)
				.withExpiresAt(validity)
				.sign(algorithm);
	}
	

	public Authentication validateToken(String token) {
		
		Algorithm algorithm = Algorithm.HMAC256(secretkey);
		
		JWTVerifier verifier = JWT.require(algorithm).build();
		
		DecodedJWT decoded = verifier.verify(token);
		
		UserDto user = AuthenticationService.findByLogin(decoded.getIssuer());
		
		return new UsernamePasswordAuthenticationToken(user , null , Collections.emptyList());
	}

	public Authentication validateCredentials(CredentialsDto credentialsDto) {
		
		UserDto user = authenticationService.authenticate(credentialsDto);
		return new UsernamePasswordAuthenticationToken(user, null, Collections.emptyList());
	}
}
