package com.socially.backend.service;

import java.nio.CharBuffer;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
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
import com.socially.backend.repository.PostRepository;
import com.socially.backend.repository.UserRepository;

import jakarta.validation.Valid;

@Service
public class UserService {
	
	private final UserRepository userRepository;
	private final PostRepository postRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder,PostRepository postRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.postRepository = postRepository;
    }

	public UserDto signUp(@Valid SignUpDto userDto) {
		Optional<SqlUser> optionalUser = userRepository.findByUserName(userDto.getUserName());

        if (optionalUser.isPresent()) {
            throw new AppExceptions("UserName already exists" , HttpStatus.BAD_REQUEST);
        }
        
        List<String> userFollwing = new ArrayList<>();
        userFollwing.add(userDto.getUserName());
        
        SqlUser user = new SqlUser(
                userDto.getFirstName(),
                userDto.getLastName(),
                userDto.getUserName(),
                passwordEncoder.encode(CharBuffer.wrap(userDto.getPassword())),
                LocalDateTime.now(),
                userDto.getSociallyBio(),
                userFollwing
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
                savedUser.getUserName(),
                savedUser.getSociallyBio(),
        		savedUser.getFollowing());
    }

    public SqlUser getUser(String userName) {
        return userRepository.findByUserName(userName)
                .orElseThrow(() -> new AppExceptions("User not found" , HttpStatus.NOT_FOUND));
    }

	public UserDto updateUserFollowing(String loggedInUserName,String searchedUserName) {
		SqlUser loggedInUser = getUser(loggedInUserName);
		SqlUser searchedUser = getUser(searchedUserName);
		
		List<String> loggedInUserFollowing = loggedInUser.getFollowing();
		List<String> searchedUserfollowers = searchedUser.getFollowers();
		
		if(loggedInUserFollowing.contains(searchedUserName)) {
			return new UserDto(loggedInUser.getId(),
					loggedInUser.getFirstName(),
					loggedInUser.getLastName(),
					loggedInUser.getUserName(),
					loggedInUser.getSociallyBio(),
					loggedInUser.getFollowing(),
					loggedInUser.getFollowers(),null);
		}
		else {
		searchedUserfollowers.add(loggedInUserName);
		loggedInUserFollowing.add(searchedUserName);
		
		searchedUser.setFollowers(searchedUserfollowers);
		loggedInUser.setFollowing(loggedInUserFollowing);
		
		 SqlUser savedUser = userRepository.save(loggedInUser);
		 userRepository.save(searchedUser);
		
		
		 return new UserDto(savedUser.getId(),
	                savedUser.getFirstName(),
	                savedUser.getLastName(),
	                savedUser.getUserName(),
	                savedUser.getSociallyBio(),
				 	savedUser.getFollowing(),
				 	savedUser.getFollowers(),null);
		}
		
	}

	public List<Post> getPosts(String userName) {
		
		List<Post> userPosts = postRepository.findAllByUserName(userName)
				.orElseThrow(()->new AppExceptions("incorrect userName", HttpStatus.NOT_FOUND));
		return userPosts;
		
	}

	public UserDto editUser(UserDto userDto, String userName) {


		SqlUser requiredUser = userRepository.findByUserName(userName)
				.orElseThrow(()->new AppExceptions("incorrect userName", HttpStatus.NOT_FOUND));
		
		if(userDto.getFirstName() != null)
		requiredUser.setFirstName(userDto.getFirstName());
		if(userDto.getLastName() != null)
		requiredUser.setLastName(userDto.getLastName());
		if(userDto.getSociallyBio() != null)
		requiredUser.setSociallyBio(userDto.getSociallyBio());
		
		userRepository.save(requiredUser);
		
		
		
		return new UserDto(requiredUser.getId(),
						   requiredUser.getFirstName(),
						   requiredUser.getLastName(),
						   requiredUser.getUserName(),
						   requiredUser.getSociallyBio());
		
		
		
	}

	public List<SqlUser> getAllUsers() {
		List<SqlUser> usersList = userRepository.findAll();
		return usersList;
		
	}


	
	

}
