package com.socially.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;

@Configuration
public class FIleUploadConfig {

	@Bean
	public MultipartResolver multipartResolver() {
		return new StandardServletMultipartResolver();

	}
}
