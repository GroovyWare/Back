package com.groovy.ware.common.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.groovy.ware.announce.dto.AnnounceDto;
import com.groovy.ware.employee.dto.EmployeeDto;

import lombok.Data;

@Data
public class FileDto {

	private Long fileCode;
	private String fileDiv;
	@JsonIgnore
	private EmployeeDto employee;
	@JsonIgnore
	private AnnounceDto announce;
	private String fileOriginalName;
	private String fileSavedName;
	
}

