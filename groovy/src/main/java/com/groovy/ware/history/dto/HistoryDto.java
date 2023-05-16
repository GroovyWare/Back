package com.groovy.ware.history.dto;




import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.groovy.ware.employee.dto.EmployeeDto;
import com.groovy.ware.pass.dto.PassDto;

import lombok.Data;


@Data
public class HistoryDto {
	
	private Long resHistory;
	
	private Long memCode;
	
	private PassDto pass;
	
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyMMdd")
	private Date resStart;
	
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyMMdd")
	private Date resEnd;
	
	private EmployeeDto employee;

}
