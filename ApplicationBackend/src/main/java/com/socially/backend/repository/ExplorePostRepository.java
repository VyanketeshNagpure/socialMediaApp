package com.socially.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.socially.backend.entity.ExplorePost;
import com.socially.backend.entity.Post;

public interface ExplorePostRepository extends JpaRepository<ExplorePost, Long>{
	
	Post getByPostId(Long id);

}
