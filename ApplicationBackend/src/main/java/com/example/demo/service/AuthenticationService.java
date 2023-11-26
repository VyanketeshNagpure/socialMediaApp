package com.example.demo.service;

import java.nio.CharBuffer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.dto.CredentialsDto;
import com.example.demo.dto.UserDto;


@Service
public class AuthenticationService {
	
	private final PasswordEncoder passwordEncoder;
	
	@Autowired
    public AuthenticationService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

	public static UserDto findByLogin(String login) {
		if ("login".equals(login)) {
            return new UserDto(1L, "Sergio", "Lema", "login", "token");
        }
        throw new RuntimeException("Invalid login");
	}

	public UserDto authenticate(CredentialsDto credentialsDto) {
		String encodedMasterPassword = passwordEncoder.encode(CharBuffer.wrap("the-password"));
        if (passwordEncoder.matches(CharBuffer.wrap(credentialsDto.getPassword()), encodedMasterPassword)) {
            return new UserDto(1L, "Sergio", "Lema", "login", "token");
        }
        throw new RuntimeException("Invalid password");
	}

}
