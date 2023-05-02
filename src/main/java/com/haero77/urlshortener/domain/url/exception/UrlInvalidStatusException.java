package com.haero77.urlshortener.domain.url.exception;

import org.springframework.http.HttpStatus;

import com.haero77.urlshortener.global.errors.exception.BusinessException;

public class UrlInvalidStatusException extends BusinessException {

	public UrlInvalidStatusException(String message) {
		super(message, HttpStatus.BAD_REQUEST);
	}
}
