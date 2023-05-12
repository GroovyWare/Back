package com.groovy.ware.member.dto;

import java.util.Date;

import com.groovy.ware.pass.dto.PassDto;

import lombok.Data;


@Data
public class MemberDto {
	
	private Long memCode;
	
	private String memName;
	
	private String memPhone;

	private Date memDeleteDate;
	
	private Date memStartDate;
	
	private Date memEndDate;
	



}
