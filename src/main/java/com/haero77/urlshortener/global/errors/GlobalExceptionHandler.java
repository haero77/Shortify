package com.haero77.urlshortener.global.errors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.haero77.urlshortener.global.errors.exception.BusinessException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(RuntimeException.class)
	private ResponseEntity<ErrorResponse> handleRuntimeException(RuntimeException e) {
		log.info("handleRuntimeException", e);
		return errorResponse(HttpStatus.INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
	}

	@ExceptionHandler(BusinessException.class)
	private ResponseEntity<ErrorResponse> handleRuntimeException(BusinessException e) {
		log.info("handleBusinessException", e);
		return errorResponse(e.status(), e.getMessage());
	}

	private ResponseEntity<ErrorResponse> errorResponse(HttpStatus status, String errorMessage) {
		return new ResponseEntity<>(new ErrorResponse(status, errorMessage), status);
	}
}
