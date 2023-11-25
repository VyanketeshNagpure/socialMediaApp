package com.example.demo.model;

import java.util.List;

import com.example.demo.dao.Comment;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Post {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	Long postId;
	Long userId;
	String userName;
	Integer noOfComments = 0;
	@ElementCollection  
	List<Comment> Comments;
	Integer likes = 0;
	String captions;
	String image;
	
	
	
	public Post() {
		super();
	}
	
	
	public Post(Long postId, Long userId, String userName, Integer noOfComments, List<Comment> comments, Integer likes,
			String captions, String image) {
		super();
		this.postId = postId;
		this.userId = userId;
		this.userName = userName;
		this.noOfComments = noOfComments;
		Comments = comments;
		this.likes = likes;
		this.captions = captions;
		this.image = image;
	}


	public Long getPostId() {
		return postId;
	}


	public void setPostId(Long postId) {
		this.postId = postId;
	}


	public Long getUserId() {
		return userId;
	}


	public void setUserId(Long userId) {
		this.userId = userId;
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public Integer getNoOfComments() {
		return noOfComments;
	}


	public void setNoOfComments(Integer noOfComments) {
		this.noOfComments = noOfComments;
	}


	public List<Comment> getComments() {
		return Comments;
	}


	public void setComments(List<Comment> comments) {
		Comments = comments;
	}


	public Integer getLikes() {
		return likes;
	}


	public void setLikes(Integer likes) {
		this.likes = likes;
	}


	public String getCaptions() {
		return captions;
	}


	public void setCaptions(String captions) {
		this.captions = captions;
	}


	public String getImage() {
		return image;
	}


	public void setImage(String image) {
		this.image = image;
	}


	


}
