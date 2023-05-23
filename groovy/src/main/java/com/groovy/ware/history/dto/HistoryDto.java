package com.groovy.ware.history.dto;



import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.groovy.ware.member.dto.MemberEmployeeDto;
import com.groovy.ware.pass.dto.PassDto;

import lombok.Data;


@Data
public class HistoryDto {
	
	private Long resHistory;
	
	private Long memCode;
	
	private PassDto pass;
	

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date resStart;
	

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date resEnd;
	
	private MemberEmployeeDto employee;

}
