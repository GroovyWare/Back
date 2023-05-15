package com.groovy.ware.approveLine.dto;

import java.util.Date;

import com.groovy.ware.approval.dto.ApprovalDto;
import com.groovy.ware.employee.dto.EmployeeDto;

import lombok.Data;

@Data
public class ApproveLineDto {
	
	private ApprovalDto approval;
	private EmployeeDto employee;
	private String aplNum;
	private String aplStatus;
	private Date aplDate;
	private String aplYn;

}
