package com.groovy.ware.employee.dto;


import lombok.Data;

@Data
public class EmpAuthDto {

	private Long empAuthCode;
	private Long empCode;
	private Long AuthCode;
	private AuthorityDto auth;
	
}