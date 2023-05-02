package com.haero77.urlshortener.domain.url.exception;

import org.springframework.http.HttpStatus;

import com.haero77.urlshortener.global.errors.exception.BusinessException;

public class ShortUrlNotFoundException extends BusinessException {

	public ShortUrlNotFoundException(String message) {
		super(message, HttpStatus.NOT_FOUND);
	}
}
