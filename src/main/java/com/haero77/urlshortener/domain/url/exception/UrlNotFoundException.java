package com.haero77.urlshortener.domain.url.exception;

import org.springframework.http.HttpStatus;

import com.haero77.urlshortener.global.errors.exception.BusinessException;

public class UrlNotFoundException extends BusinessException {

	public UrlNotFoundException(String message) {
		super(message, HttpStatus.NOT_FOUND);
	}
}
