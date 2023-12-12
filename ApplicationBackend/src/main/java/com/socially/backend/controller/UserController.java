package com.socially.backend.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.socially.backend.dto.UserDto;
import com.socially.backend.entity.Post;
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
		
		
		return ResponseEntity.status(HttpStatus.FOUND)
							 .body(new UserDto( requiredUser.getId(),
												requiredUser.getFirstName(),
												requiredUser.getLastName(),
												requiredUser.getUserName(),
												requiredUser.getSociallyBio(),
												requiredUser.getFollowing(),
												requiredUser.getFollowers(),null));
	}
	
	@GetMapping("/search/users")
	public ResponseEntity<List<SqlUser>> getALlUsers(){
		List<SqlUser> allUsers = userService.getAllUsers();
		return ResponseEntity.status(HttpStatus.OK).body(allUsers);
	}
	
	@GetMapping("getPost/{userName}")
	public ResponseEntity<List<Post>> getPostsByUserName(@PathVariable String userName){
		List<Post> userPosts = userService.getPosts(userName);
		
		return ResponseEntity.status(HttpStatus.FOUND).body(userPosts);
	}
	
	@PutMapping("edit/{userName}")
	public ResponseEntity<UserDto> editUser(@RequestBody UserDto userDto,@PathVariable String userName){
		
		UserDto newUserDto = userService.editUser(userDto,userName);
		
		return ResponseEntity.status(HttpStatus.OK).body(newUserDto);
		
	}
	
	@PutMapping("updateUser/{loggedInUser}/search/{searchedUserName}")
	public ResponseEntity<UserDto> updateUserFollowing(@PathVariable("loggedInUser") String loggedInUserName , 
													@PathVariable("searchedUserName") String searchedUserName ){
		
		UserDto userData = userService.addToUserFollowing(loggedInUserName,searchedUserName);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(userData);
	}
	
	@PutMapping("updateUser/{loggedInUser}/unfollow/{searchedUserName}")
	public ResponseEntity<UserDto> removeFromUserFollowing(@PathVariable("loggedInUser") String loggedInUserName , 
			@PathVariable("searchedUserName") String searchedUserName ){
		
		UserDto userData = userService.removeFromUserFollowing(loggedInUserName,searchedUserName);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(userData);
	}
	
	
}
