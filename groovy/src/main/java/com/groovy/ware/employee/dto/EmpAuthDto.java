package com.groovy.ware.employee.dto;

import com.groovy.ware.employee.entity.EmpAuthPK;

import lombok.Data;

@Data
public class EmpAuthDto {

	private EmpAuthPK empAuthPK;
	private AuthorityDto auth;
	
}
