package com.socially.backend.service;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.socially.backend.entity.Post;
import com.socially.backend.repository.PostRepository;

@Service
public class PostService {
	
	private final PostRepository postRepository;

	@Autowired
	public PostService(PostRepository postRepository) {
		super();
		this.postRepository = postRepository;
	}

	public void savePost(MultipartFile image, Post post) throws IllegalStateException, IOException {
			
		String imagePath = saveImage(image);

        // Set the image path in the Post object
        post.setImage(imagePath);

        // Save the Post object
        postRepository.save(post);
		
	}
	
	private String saveImage(MultipartFile image) throws IllegalStateException, IOException {
        // Implement logic to save the image to a specific location and return the image path
        // You may use a service for image storage

        // Example:
        // String imagePath = imageStorageService.saveImage(image);
        // return imagePath;

        // For simplicity, just returning the original filename
		
		
		image.transferTo( new File("d:\\" + image.getOriginalFilename()));
        return ("d:\\" + image.getOriginalFilename());
    }
	
	

}
