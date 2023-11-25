package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.ExplorePost;
import com.example.demo.model.Post;

public interface ExplorePostRepository extends JpaRepository<ExplorePost, Long>{
	
	Post getByPostId(Long id);

}
