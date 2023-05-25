package com.groovy.ware.approval.entity;

import java.io.Serializable;

import javax.persistence.Column;

import lombok.Data;

@Data
public class ReaderId implements Serializable {
	
	private Integer apvCode;
	private Integer empCode;

}
