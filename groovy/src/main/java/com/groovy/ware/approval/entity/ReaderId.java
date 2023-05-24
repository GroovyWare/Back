package com.groovy.ware.approval.entity;

import java.io.Serializable;

import javax.persistence.Column;

import lombok.Data;

@Data
public class ReaderId implements Serializable {
	
	@Column(name="APV_CODE")
	private Integer apvCode;
	
	@Column(name="EMP_CODE")
	private Integer empCode;

}
