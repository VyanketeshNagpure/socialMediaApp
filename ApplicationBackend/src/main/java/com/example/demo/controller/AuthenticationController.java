package com.example.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.config.UserAuthenticationProvider;
import com.example.demo.dto.UserDto;
import com.example.demo.service.UserService;

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
        user.setToken(userAuthenticationProvider.createToken(user.getLogin()));
        return ResponseEntity.ok(user);
    }

//    @PostMapping("/signUp")
//    public ResponseEntity<UserDto> signUp(@RequestBody @Valid SignUpDto user) {
//        UserDto createdUser = userService.signUp(user);
//        return ResponseEntity.created(URI.create("/users/" + createdUser.getId() + "/profile")).body(createdUser);
//    }
//
//    @PostMapping("/signOut")
//    public ResponseEntity<Void> signOut(@AuthenticationPrincipal UserDto user) {
//        SecurityContextHolder.clearContext();
//        return ResponseEntity.noContent().build();
//    }

}
