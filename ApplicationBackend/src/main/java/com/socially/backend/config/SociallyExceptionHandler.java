package com.socially.backend.config;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.socially.backend.dto.ErrorDto;
import com.socially.backend.exceptions.AppExceptions;

@ControllerAdvice
public class SociallyExceptionHandler {
	
	@ExceptionHandler(value = {AppExceptions.class})
	@ResponseBody
	public ResponseEntity<ErrorDto> handleExceptions(AppExceptions ex){
		return ResponseEntity
				.status(ex.getStatus())
				.body(new ErrorDto(ex.getMessage()));
	}

}
