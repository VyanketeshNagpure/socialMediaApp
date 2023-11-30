package com.socially.backend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.socially.backend.entity.Post;

public interface PostRepository extends JpaRepository<Post, Long>{
	
	Post getByPostId(Long id);
	Optional<List<Post>> findAllByUserName(String userName);

}
