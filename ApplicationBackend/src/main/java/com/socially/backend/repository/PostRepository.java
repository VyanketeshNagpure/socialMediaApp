package com.socially.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.socially.backend.entity.Post;

public interface PostRepository extends JpaRepository<Post, Long>{
	
	Post getByPostId(Long id);

}
