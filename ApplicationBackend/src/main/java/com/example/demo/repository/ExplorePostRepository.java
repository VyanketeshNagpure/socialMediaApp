package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.ExplorePost;
import com.example.demo.entity.Post;

public interface ExplorePostRepository extends JpaRepository<ExplorePost, Long>{
	
	Post getByPostId(Long id);

}
