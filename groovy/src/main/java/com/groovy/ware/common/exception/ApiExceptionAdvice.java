package com.groovy.ware.common.exception;

import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.groovy.ware.common.exception.dto.ApiExceptionDto;

import ch.qos.logback.classic.Logger;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@RestControllerAdvice
public class ApiExceptionAdvice {
	
	@ExceptionHandler({UserNotFoundException.class, DuplicatedEmpIdException.class, LoginFailedException.class, IllegalArgumentException.class})
	public ResponseEntity<ApiExceptionDto> exceptionHandler(Exception e){
		
		return ResponseEntity
				.status(HttpStatus.BAD_REQUEST)
				.body(new ApiExceptionDto(HttpStatus.BAD_REQUEST, e.getMessage()));

	}
	
	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<ApiExceptionDto> exceptionHandler(RuntimeException e){
		
		return ResponseEntity
				.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(new ApiExceptionDto(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage()));
		
	}

	@ExceptionHandler(NullPointerException.class)
	public ResponseEntity<ApiExceptionDto> exceptionHandler(NullPointerException e) {
		org.slf4j.Logger logger = LoggerFactory.getLogger(ApiExceptionAdvice.class);
		logger.error("NullPointerException occurred", e);
	
		return ResponseEntity
				.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(new ApiExceptionDto(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error"));
	}

}
