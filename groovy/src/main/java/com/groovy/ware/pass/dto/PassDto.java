package com.groovy.ware.pass.dto;

import lombok.Data;

@Data
public class PassDto {
	
	private Long passCode;
	
	private String passType;

	private Long passPrice;
	
	private Long passAmount;
	
	private String passEtc;

}
