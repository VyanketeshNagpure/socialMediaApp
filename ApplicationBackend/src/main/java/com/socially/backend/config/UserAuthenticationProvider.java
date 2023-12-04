package com.socially.backend.config;

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
import com.socially.backend.dto.CredentialsDto;
import com.socially.backend.dto.UserDto;
import com.socially.backend.service.AuthenticationService;

import io.jsonwebtoken.Jwts;
import jakarta.annotation.PostConstruct;

@Component
public class UserAuthenticationProvider {
	
	@Value("${security.jwt.token.secret-key:secret_key}")
	private String secretKey;
	
	private final AuthenticationService authenticationService;
	
	@Autowired
	public UserAuthenticationProvider(AuthenticationService authenticationService) {
		super();
		this.authenticationService = authenticationService;
	}

	@PostConstruct
	protected void init() {
		secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
	}
	
	
	public String createToken(String userName) {
		
		Algorithm algorithm = Algorithm.HMAC256(secretKey);
		
		Date now = new Date();
		Date validity = new Date(now.getTime() + 3600000); // validity 1 hr
		
		return JWT.create()
				.withIssuer(userName)
				.withIssuedAt(now)
				.withExpiresAt(validity)
				.sign(algorithm);
	}
	

	public Authentication validateToken(String token) {
		
		Algorithm algorithm = Algorithm.HMAC256(secretKey);
		
		JWTVerifier verifier = JWT.require(algorithm).build();
		
		DecodedJWT decoded = verifier.verify(token);
		UserDto user = authenticationService.findByUserName(decoded.getIssuer());
		
//		 String login = Jwts.parser()
//	                .setSigningKey(secretKey)
//	                .parseClaimsJws(token)
//	                .getBody()
//	                .getSubject();
//		
//		UserDto user = authenticationService.findByUserName(login);
		
		return new UsernamePasswordAuthenticationToken(user , null , Collections.emptyList());
	}

	public Authentication validateCredentials(CredentialsDto credentialsDto) {
		
		UserDto user = authenticationService.authenticate(credentialsDto);
		return new UsernamePasswordAuthenticationToken(user, null, Collections.emptyList());
	}
}
