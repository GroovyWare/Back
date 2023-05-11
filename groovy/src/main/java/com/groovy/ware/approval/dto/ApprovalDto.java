package com.groovy.ware.approval.dto;

import java.util.Date;

import javax.persistence.Column;

import com.groovy.ware.document.dto.DocumentDto;

import lombok.Data;

@Data
public class ApprovalDto {
	
	private Long apvCode;
	private Date apvCreatedDate;
	private String apvStatus;
	private Date apvEndDate;
	private EmployeeDto employee;
	private DocumentDto document;
	private String apvContext;

}
