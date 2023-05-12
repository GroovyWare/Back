package com.groovy.ware.common;

import org.springframework.http.HttpStatus;

import lombok.Data;

@Data
public class ResponseDto {

	private int status;
	private String message;
	private Object data;
	

	public ResponseDto(HttpStatus status, String message) {
		this.status = status.value();
		this.message = message;
	}
	
	public ResponseDto(HttpStatus status, String message, Object data) {
		this.status = status.value();
		this.message = message;
		this.data = data;		
	}

}