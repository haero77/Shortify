package com.haero77.urlshortener.domain.url.exception;

import org.springframework.http.HttpStatus;

import com.haero77.urlshortener.global.errors.exception.BusinessException;

public class ShortUrlInvalidStatusException extends BusinessException {
	public ShortUrlInvalidStatusException(String message) {
		super(message, HttpStatus.BAD_REQUEST);
	}
}
