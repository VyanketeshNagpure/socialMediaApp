package com.socially.backend.controller;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.socially.backend.config.UserAuthenticationProvider;
import com.socially.backend.dto.SignUpDto;
import com.socially.backend.dto.UserDto;
import com.socially.backend.service.UserService;

import jakarta.validation.Valid;

@RestController
public class AuthenticationController {
	
	private final UserService userService;
    private final UserAuthenticationProvider userAuthenticationProvider;

    public AuthenticationController(UserService userService,
                                    UserAuthenticationProvider userAuthenticationProvider) {
        this.userService = userService;
        this.userAuthenticationProvider = userAuthenticationProvider;
    }

    @PostMapping("/login")
    public ResponseEntity<UserDto> signIn(@AuthenticationPrincipal UserDto user) {
        user.setToken(userAuthenticationProvider.createToken(user.getUserName()));
        return ResponseEntity.ok(user);
    }

    @PostMapping("/signup")
    public ResponseEntity<UserDto> signUp(@RequestBody @Valid SignUpDto user) {
        UserDto createdUser = userService.signUp(user);
        createdUser.setToken(userAuthenticationProvider.createToken(user.getUserName()));
        ResponseEntity<UserDto> response = ResponseEntity.created(URI.create("/users/" + createdUser.getId() + "/profile")).body(createdUser);
        return response;
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> signOut(@AuthenticationPrincipal UserDto user) {
        SecurityContextHolder.clearContext();
        return ResponseEntity.noContent().build();
    }

}
