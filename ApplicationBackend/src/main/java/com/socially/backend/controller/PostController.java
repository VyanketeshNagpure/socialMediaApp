package com.socially.backend.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.socially.backend.dao.Comment;
import com.socially.backend.entity.ExplorePost;
import com.socially.backend.entity.Post;
import com.socially.backend.repository.ExplorePostRepository;
import com.socially.backend.repository.PostRepository;
import com.socially.backend.service.PostService;
import com.socially.backend.service.UserService;

@RestController
public class PostController {
	
	private final PostRepository postRepository;
	private final ExplorePostRepository explorePostRepository;
	private final PostService postService;

	@Autowired
	public PostController(PostRepository postRepository, ExplorePostRepository explorePostRepository,
			PostService postService) {
		super();
		this.postRepository = postRepository;
		this.explorePostRepository = explorePostRepository;
		this.postService = postService;
	}

	@CrossOrigin(origins = "http://localhost:8080")
	@PostMapping(path ="/feed",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<Post> savePostDetails(@RequestPart("image") MultipartFile image,
            									@RequestPart("post") Post post) throws IllegalStateException, IOException {
		
		postService.savePost(image,post);
		
		return ResponseEntity.status(HttpStatus.OK)
				.body(post);
	}
	
	@CrossOrigin(origins = "http://localhost:8080")
	@GetMapping("feed/{loggedInUserName}")
	public ResponseEntity<List<Post>> getAllDetails(@PathVariable String loggedInUserName){

		List<Post> findAll = postService.getAllUserFeed(loggedInUserName);
		return ResponseEntity.status(HttpStatus.FOUND)
				.body(findAll);
	}
	
	
	@PutMapping("/feed/{postId}")
	public ResponseEntity<Post> updatePostDetails(@RequestBody Post post,@PathVariable Long postId, ModelMap model){
		
		Post requiredPost = postRepository.getByPostId(postId);
		
		if(post.getCaptions() != null)
		requiredPost.setCaptions(post.getCaptions());
		
		if(post.getNoOfComments() != null )
		requiredPost.setNoOfComments(post.getNoOfComments());
		
		//requiredPost.setImage(post.getImage());
		if(post.getLikes() != null)
		requiredPost.setLikes(post.getLikes());
		
		postRepository.save(requiredPost);
		
		return ResponseEntity.status(HttpStatus.OK)
				.body(requiredPost);
	}
	
	@PostMapping("feed/{postId}/comments")
	public ResponseEntity<Comment> postComments(@PathVariable Long postId,@RequestBody Comment commentBody){
		
		Post requiredPost = postRepository.getByPostId(postId);
		
		List<Comment> allComments = requiredPost.getComments();
		
		allComments.add(commentBody);
		requiredPost.setNoOfComments(requiredPost.getNoOfComments()+1);
		
		requiredPost.setComments(allComments);
		
		postRepository.save(requiredPost);
		
		return ResponseEntity.status(HttpStatus.OK)
				.body(commentBody);
	}
	
	@GetMapping("feed/{postId}/comments")
	public ResponseEntity<List<Comment>> getAllComments(@PathVariable Long postId){
		
		Post requiredPost = postRepository.getByPostId(postId);
		
		List<Comment> listOfComments = requiredPost.getComments();
		
		return ResponseEntity.status(HttpStatus.OK)
				.body(listOfComments);
	}
		
	
	@PostMapping("/explore")
	public ResponseEntity<ExplorePost> saveExplorePostDetails(@RequestBody ExplorePost post) {
		
		explorePostRepository.save(post);
		
		return ResponseEntity.status(HttpStatus.OK)
				.body(post);
	}
	
	@GetMapping("/explore")
	public ResponseEntity<List<ExplorePost>> getAllExploreDetails(){
		
		List<ExplorePost> findAll = explorePostRepository.findAll();
		return ResponseEntity.status(HttpStatus.FOUND)
				.body(findAll);
	}
	
	

}
