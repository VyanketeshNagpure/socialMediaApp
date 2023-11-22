package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Post;
import com.example.demo.repository.PostRepository;

@RestController
public class PostController {
	
	@Autowired
	PostRepository postRepository;
	
	//fefwfaf;
	
	@PostMapping("/feed")
	public ResponseEntity<Post> savePostDetails(@RequestBody Post post) {
		
		postRepository.save(post);
		
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(post);
	}
	
	@GetMapping("/feed")
	public ResponseEntity<List<Post>> getAllDetails(){
		
		List<Post> findAll = postRepository.findAll();
		return ResponseEntity.status(HttpStatus.FOUND)
				.body(findAll);
	}

}
