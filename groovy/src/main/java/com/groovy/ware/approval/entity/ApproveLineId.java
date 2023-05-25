package com.groovy.ware.approval.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Data;

@Data
public class ApproveLineId implements Serializable{
	
	private Integer apvCode;
	private Integer empCode;

}
