package com.haero77.urlshortener.global.errors;

import org.springframework.http.HttpStatus;

public record ErrorResponse(
	HttpStatus status,
	String errorMessage
) {
}
