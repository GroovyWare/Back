package com.groovy.ware.common.dto;

import lombok.Data;

@Data
public class FileDto {

	private Long fileCode;
	private String fileDiv; 
	private Long empCode;
	private Long annCode;	
	private String fileOriginalName;
	private String fileSavedName;

}