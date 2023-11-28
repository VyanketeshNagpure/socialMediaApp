package com.socially.backend.service;

import java.nio.CharBuffer;
import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.socially.backend.dto.SignUpDto;
import com.socially.backend.dto.UserDto;
import com.socially.backend.entity.Post;
import com.socially.backend.entity.SqlUser;
import com.socially.backend.exceptions.AppExceptions;
import com.socially.backend.repository.UserRepository;

import jakarta.validation.Valid;

@Service
public class UserService {
	
	private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

	public UserDto signUp(@Valid SignUpDto userDto) {
		Optional<SqlUser> optionalUser = userRepository.findByUserName(userDto.getUserName());

        if (optionalUser.isPresent()) {
            throw new AppExceptions("UserName already exists" , HttpStatus.BAD_REQUEST);
        }

        SqlUser user = new SqlUser(
                userDto.getFirstName(),
                userDto.getLastName(),
                userDto.getUserName(),
                passwordEncoder.encode(CharBuffer.wrap(userDto.getPassword())),
                LocalDateTime.now()
                );
        
//        SqlUser user = new SqlUser();
//        user.setFirstName(userDto.getFirstName());
//        user.setLastName(userDto.getLastName());
//        user.setLogin(userDto.getLogin());
//        user.setPassword(passwordEncoder.encode(CharBuffer.wrap(userDto.getPassword())));
//        user.setCreatedDate(LocalDateTime.now());

        SqlUser savedUser = userRepository.save(user);

        return new UserDto(savedUser.getId(),
                savedUser.getFirstName(),
                savedUser.getLastName(),
                savedUser.getUserName());
    }

    private SqlUser getUser(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new AppExceptions("User not found" , HttpStatus.NOT_FOUND));
    }


	
	

}
