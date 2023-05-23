package com.groovy.ware.common.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.groovy.ware.announce.dto.AnnounceDto;
import com.groovy.ware.employee.dto.EmployeeDto;

import lombok.Data;

@Data
public class FileDto {

	private Long fileCode;
	private String fileDiv;
	private Long empCode;
	private Long annCode;
	private String fileOriginalName;
	private String fileSavedName;
	
	@JsonIgnore
	private EmployeeDto employee;
	
	@JsonIgnore
	private AnnounceDto announce;

	@Override
	public String toString() {
		return "FileDto [fileCode=" + fileCode + ", fileDiv=" + fileDiv + ", empCode=" + empCode + ", annCode="
				+ annCode + ", fileOriginalName=" + fileOriginalName + ", fileSavedName=" + fileSavedName
				+ ", announce=" + announce + "]";
	}
	
	
}

