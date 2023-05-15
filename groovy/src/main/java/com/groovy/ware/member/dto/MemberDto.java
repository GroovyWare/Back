package com.groovy.ware.member.dto;

import java.util.Date;
import java.util.List;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.groovy.ware.history.dto.HistoryDto;
import com.groovy.ware.history.entity.History;

import lombok.Data;


@Data
public class MemberDto {
	
	private Long memCode;
	
	private String memName;
	
	private String memPhone;

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyMMdd")
	private Date memDeleteDate;
	
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyMMdd")
	private Date memStartDate;
	
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyMMdd")
	private Date memEndDate;
	
	private List<HistoryDto> history;
	
	private Long empCode;
	
	



}
