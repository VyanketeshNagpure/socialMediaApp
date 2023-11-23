package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.example.demo.model.Post;
import com.example.demo.repository.PostRepository;

@RestController
public class PostController {
	
	@Autowired
	PostRepository postRepository;
	@CrossOrigin(origins = "http://localhost:8080")
	@PostMapping("/feed")
	public ResponseEntity<Post> savePostDetails(@RequestBody Post post) {
		
		postRepository.save(post);
		
		return ResponseEntity.status(HttpStatus.OK)
				.body(post);
	}
	
	@CrossOrigin(origins = "http://localhost:8080")
	@GetMapping("/feed")
	public ResponseEntity<List<Post>> getAllDetails(){
		
		List<Post> findAll = postRepository.findAll();
		return ResponseEntity.status(HttpStatus.FOUND)
				.body(findAll);
	}
	
	@PutMapping("/feed/{postId}")
	public ResponseEntity<Post> updatePostDetails(@RequestBody Post post,@PathVariable Long postId, ModelMap model){
		
		Post requiredPost = postRepository.getByPostId(postId);
		
		requiredPost.setCaptions(post.getCaptions());
		requiredPost.setComments(post.getComments());
		//requiredPost.setImage(post.getImage());
		requiredPost.setLikes(post.getLikes());
		
		postRepository.save(requiredPost);
		
		return ResponseEntity.status(HttpStatus.OK)
				.body(requiredPost);
	}

}
