package com.groovy.ware.approval.dto;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.groovy.ware.document.dto.DocumentDto;
import com.groovy.ware.employee.dto.EmployeeDto;

import lombok.Data;

@Data
public class ApprovalDto {
	
	private Integer apvCode;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date apvCreatedDate;
	private String apvStatus;
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date apvEndDate;
	private EmployeeDto employee;
	private DocumentDto document;
	private String apvContext;
	private List<ApproveLineDto> approveLine;
	private List<ReaderDto> readerLine;
	private Date vacStartDate;
	private Date vacEndDate;
}
