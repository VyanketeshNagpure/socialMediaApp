package com.socially.backend.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.socially.backend.entity.Post;
import com.socially.backend.entity.SqlUser;
import com.socially.backend.repository.PostRepository;
import com.socially.backend.repository.UserRepository;

@Service
public class PostService {
	
	private final PostRepository postRepository;
	private final UserRepository userRepository;
	

	@Autowired
	public PostService(PostRepository postRepository,UserRepository userRepository) {
		super();
		this.postRepository = postRepository;
		this.userRepository = userRepository ;
	}

	public void savePost(MultipartFile image, Post post) throws IllegalStateException, IOException {
			
		String imagePath = saveImage(image);

        // Set the image path in the Post object
        post.setImagePath(imagePath);

        // Save the Post object
        postRepository.save(post);
		
	}
	
	private String saveImage(MultipartFile image) throws IllegalStateException, IOException {
      
		image.transferTo( new File("d:\\" + image.getOriginalFilename()));
        return ("d:\\" + image.getOriginalFilename());
    }

	public List<Post> getAllUserFeed(String loggedInUserName) {
		
		
		Optional<SqlUser> loggedInUser = userRepository.findByUserName(loggedInUserName);
		List<Post> loggedInUserFeed = new ArrayList<Post>();
		
		if (loggedInUser.isPresent()) {
			SqlUser sqlUser = loggedInUser.get();
			List<String> following = sqlUser.getFollowing();

			for (String user : following) {
				Optional<List<Post>> postListOptional = postRepository.findAllByUserName(user);
				if (postListOptional.isPresent()) {
		            List<Post> postList = postListOptional.get();
		            loggedInUserFeed.addAll(postList);
		        }
			}
		}
		return loggedInUserFeed;
	}
	
	

}
