package com.socially.backend.dao;

import java.io.Serializable;

import jakarta.persistence.Embeddable;

@Embeddable
public class Comment implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String commentatorsUserName;
	String comment;
	
	
	
	public Comment() {
		super();
	}
	public Comment(String commentatorsUserName, String comment) {
		super();
		this.commentatorsUserName = commentatorsUserName;
		this.comment = comment;
	}
	public String getUserName() {
		return commentatorsUserName;
	}
	public void setUserName(String commentatorsUserName) {
		this.commentatorsUserName = commentatorsUserName;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	@Override
	public String toString() {
		return "Comment [commentatorsUserName=" + commentatorsUserName + ", comment=" + comment + "]";
	}
	
	
	
	

}
