package com.socially.backend.service;

import java.nio.CharBuffer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.socially.backend.dto.CredentialsDto;
import com.socially.backend.dto.UserDto;
import com.socially.backend.entity.SqlUser;
import com.socially.backend.exceptions.AppExceptions;
import com.socially.backend.repository.UserRepository;


@Service
public class AuthenticationService {
	
	private final PasswordEncoder passwordEncoder;
	
	private final UserRepository userRepository;
	
	@Autowired
    public AuthenticationService(PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

	public UserDto findByUserName(String userName) {
		SqlUser user = userRepository.findByUserName(userName)
                .orElseThrow(() -> new AppExceptions("Token not found",HttpStatus.NOT_FOUND));
        return new UserDto(user.getId(),
                user.getFirstName(), 
                user.getLastName(),
                user.getUserName(),
        		user.getSociallyBio());
	}

	public UserDto authenticate(CredentialsDto credentialsDto) {
		SqlUser user = userRepository.findByUserName(credentialsDto.getUserName())
                .orElseThrow(() -> new AppExceptions("User not found",HttpStatus.NOT_FOUND));

        if (passwordEncoder.matches(CharBuffer.wrap(credentialsDto.getPassword()), user.getPassword())) {

            return new UserDto(user.getId(),
                    user.getFirstName(),
                    user.getLastName(),
                    user.getUserName(),
                    user.getSociallyBio());
        }
        throw new AppExceptions("Invalid password",HttpStatus.BAD_REQUEST);
	}

}
