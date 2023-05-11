package com.groovy.ware.favorite.dto;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.groovy.ware.document.dto.DocumentDto;

@Data
public class FavoriteDto {
	
	private EmployeeDto employee;
	private DocumentDto document;

}
