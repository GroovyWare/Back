package com.groovy.ware.approval.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Data;

@Data
@Embeddable
public class ApproveLineId implements Serializable{
	
	@Column(name="APV_CODE")
	private Long apvCode;
	
	@Column(name="EMP_CODE")
	private Long empCode;

}
