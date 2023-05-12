package com.groovy.ware.history.dto;

import java.util.Date;

import com.groovy.ware.member.dto.MemberDto;
import com.groovy.ware.pass.dto.PassDto;

import lombok.Data;


@Data
public class HistoryDto {
	
	private String resHistory;
	
	private MemberDto memCode;

	private PassDto passCode;
	
	private Date resStart;

	private Date resEnd;

}
