package com.groovy.ware.document.dto;

import java.util.Date;

import javax.persistence.Column;

import lombok.Data;

@Data
public class DocumentDto {
	
	public Long docCode;
	private String docCustom;
	private String docTitle;
	private String docContext;
	private Date docStartDate;
	private Date docEndDate;
}
