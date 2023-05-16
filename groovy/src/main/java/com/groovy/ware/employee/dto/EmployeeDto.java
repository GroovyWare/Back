package com.groovy.ware.employee.dto;

import java.util.Date;

import lombok.Data;

@Data
public class EmployeeDto {

	
	private Long empCode;	
	private String empId;
	private String empPassword;
	private String empName;
	private String empPhone;
	private String empEmail;
	private String empAddress;
	private Date entDate;
	private Date exDate;
	private String empStatus;
	private DepartmentDto department;
	private PositionDto position;
}
