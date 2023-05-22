package com.groovy.ware.approval.dto;

import java.util.Date;

import com.groovy.ware.approval.entity.ApproveLineId;

import lombok.Data;

@Data
public class ApproveLineDto {
	
	private ApproveLineId approveLineId;
	private String aplNum;
	private String aplStatus;
	private Date aplDate;
	private String aplYn;

}
