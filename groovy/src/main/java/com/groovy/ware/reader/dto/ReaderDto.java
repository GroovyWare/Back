package com.groovy.ware.reader.dto;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.groovy.ware.approval.entity.Approval;
import com.groovy.ware.employee.entity.Employee;

import lombok.Data;

@Data
public class ReaderDto {
	
	private Approval approval;
	private Employee employee;

}
