package com.groovy.ware.favorite.dto;

import com.groovy.ware.document.dto.DocumentDto;
import com.groovy.ware.employee.dto.EmployeeDto;

import lombok.Data;

@Data
public class FavoriteDto {
	
	private EmployeeDto employee;
	private DocumentDto document;

}
