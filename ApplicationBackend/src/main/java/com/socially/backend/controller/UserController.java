package com.socially.backend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.socially.backend.dto.UserDto;
import com.socially.backend.entity.SqlUser;
import com.socially.backend.service.UserService;

@RestController
public class UserController {
	
	private final UserService userService;

	public UserController(UserService userService) {
		super();
		this.userService = userService;
	}
	
	@GetMapping("search/{userName}")
	public ResponseEntity<UserDto> getUserProfile(@PathVariable String userName){
		SqlUser requiredUser = userService.getUser(userName);
		
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
							 .body(new UserDto( requiredUser.getId(),
												requiredUser.getFirstName(),
												requiredUser.getLastName(),
												requiredUser.getUserName(),
												requiredUser.getSociallyBio(),
												requiredUser.getFollowing(),
												requiredUser.getFollowers(),null));
	}
	
	@PutMapping("updateUser/{loggedInUser}/search/{searchedUserName}")
	public ResponseEntity<UserDto> updateUserFollowing(@PathVariable("loggedInUser") String loggedInUser , 
													@PathVariable("searchedUserName") String searchedUserName ){
		
		UserDto userData = userService.updateUserFollowing(loggedInUser,searchedUserName);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(userData);
	}
}
