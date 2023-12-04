package com.socially.backend.exceptions;

import org.springframework.http.HttpStatus;

public class AppExceptions extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private final HttpStatus status;

	public AppExceptions(String message, HttpStatus status) {
		super(message);
		this.status = status;
	}

	public HttpStatus getStatus() {
		return status;
	}
	
	

}
