package com.groovy.ware.approval.dto;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.groovy.ware.approval.entity.Approval;
import com.groovy.ware.employee.entity.Employee;

import lombok.Data;

@Data
public class ReaderDto {
	
	private Integer apvCode;
	private Integer empCode;

}
