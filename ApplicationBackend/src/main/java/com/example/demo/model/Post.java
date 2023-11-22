package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Post {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	Long postId;
	Long userId;
	Integer comments;
	String captions;
	String image;
	
	
	
	public Post() {
		super();
	}
	public Post(Long postId, Long userId, Integer comments, String captions, String image) {
		super();
		this.postId = postId;
		this.userId = userId;
		this.comments = comments;
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
	public Integer getComments() {
		return comments;
	}
	public void setComments(Integer comments) {
		this.comments = comments;
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
	
	
	@Override
	public String toString() {
		return "Post [postId=" + postId + ", userId=" + userId + ", comments=" + comments + ", captions=" + captions
				+ ", image=" + image + "]";
	}
	
	
	

}
