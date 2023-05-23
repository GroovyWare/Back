package com.groovy.ware.document.dto;

import java.util.Date;

import javax.persistence.Column;

import lombok.Data;

@Data
public class DocumentDto {
	
	public Long docCode;
	private String docTitle;
	private String docContext;
}
