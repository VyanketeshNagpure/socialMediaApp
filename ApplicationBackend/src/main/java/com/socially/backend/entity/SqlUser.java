package com.socially.backend.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class SqlUser {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String firstName;
	private String lastName;
	private String userName;
	private String password;
	private List<String> following = new ArrayList<>();
	private List<String> followers = new ArrayList<>();
	private String sociallyBio;
	private LocalDateTime createdDate;

	public SqlUser() {
		super();
	}
	
	

	public SqlUser(Long id, String firstName, String lastName) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
	}


	public SqlUser(String firstName, String lastName, String userName, String password,
			LocalDateTime createdDate,String sociallyBio) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.password = password;
		this.createdDate = createdDate;
		this.sociallyBio = sociallyBio;
	}
	

	public SqlUser(Long id, String firstName, String lastName, String userName, String password, List<String> following,
			List<String> followers, String sociallyBio, LocalDateTime createdDate) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.password = password;
		this.following = following;
		this.followers = followers;
		this.sociallyBio = sociallyBio;
		this.createdDate = createdDate;
	}

	

	public List<String> getFollowing() {
		return following;
	}



	public void setFollowing(List<String> following) {
		this.following = following;
	}



	public List<String> getFollowers() {
		return followers;
	}



	public void setFollowers(List<String> followers) {
		this.followers = followers;
	}



	public String getSociallyBio() {
		return sociallyBio;
	}



	public void setSociallyBio(String sociallyBio) {
		this.sociallyBio = sociallyBio;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

	@Override
	public String toString() {
		return "SqlUser [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", userName=" + userName
				+ ", password=" + password + ", createdDate=" + createdDate + "]";
	}

}
