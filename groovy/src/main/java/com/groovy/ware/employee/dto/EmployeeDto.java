package com.groovy.ware.employee.dto;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.groovy.ware.common.dto.FileDto;

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
	private Date empEntDate;
	private Date empExDate;
	private String EmpStatus;
	private DepartmentDto dept;
	private PositionDto position;
	private FileDto file;
	
	@JsonIgnore
	private MultipartFile imgUrl;
}
