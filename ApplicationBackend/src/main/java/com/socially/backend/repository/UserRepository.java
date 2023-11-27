package com.socially.backend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.socially.backend.entity.SqlUser;

public interface UserRepository extends JpaRepository<SqlUser, Long> {
	
	Optional<SqlUser> findByUserName(String login);

//    @Query("{$or: [{firstName: /?0/}, {lastName: /?0/}, {login: /?0/}]}")
//    List<SqlUser> searchUsers(String term);

}
