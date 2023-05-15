package com.groovy.ware.play.dto;

import java.util.Date;

import com.groovy.ware.employee.dto.EmployeeDto;

import lombok.Data;

@Data
public class PlayDto {
	
	private Long playCode;
	private EmployeeDto employeeDto;
	private String plyType;
	private Date plyTime;
	private String plyPlay;
	private String plyWeight;
	private Integer plyNum;

}
