package com.socially.backend.entity;

import java.util.List;

import com.socially.backend.dao.Comment;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;

@Entity
public class Post {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	Long postId;
	
	@NotEmpty
	String userName;
	
	Integer noOfComments = 0;
	
	@ElementCollection  
	List<Comment> Comments;
	
	Integer likes;
	
	String captions;
	
	
	String imagePath;
	
	
	public Post() {
		super();
	}



	public Post(Long postId, String userName, Integer noOfComments, List<Comment> comments, Integer likes,
			String captions, String imagePath) {
		super();
		this.postId = postId;
		this.userName = userName;
		this.noOfComments = noOfComments;
		this.Comments = comments;
		this.likes = likes;
		this.captions = captions;
		this.imagePath = imagePath;
	}


	public Long getPostId() {
		return postId;
	}


	public void setPostId(Long postId) {
		this.postId = postId;
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


	public String getImagePath() {
		return imagePath;
	}


	public void setImagePath(String image) {
		this.imagePath = image;
	}


	


}
