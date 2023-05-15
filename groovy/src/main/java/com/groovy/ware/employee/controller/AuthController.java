package com.groovy.ware.employee.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.groovy.ware.common.dto.ResponseDto;
import com.groovy.ware.employee.dto.EmployeeDto;
import com.groovy.ware.employee.service.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {

	private final AuthService authService;
	
	public AuthController(AuthService authService) {
		this.authService = authService;
	}
	
	/* 로그인 */
	@PostMapping("/login")
	public ResponseEntity<ResponseDto> login(@RequestBody EmployeeDto employeeDto) {
		
		return ResponseEntity
				.ok()
				.body(new ResponseDto(HttpStatus.OK, "로그인 완료", authService.login(employeeDto)));	
	}
	
}
