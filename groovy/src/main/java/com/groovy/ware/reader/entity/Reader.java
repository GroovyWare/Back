package com.groovy.ware.reader.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.groovy.ware.approval.entity.Approval;
import com.groovy.ware.employee.entity.Employee;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="GRV_READER")
public class Reader implements Serializable{
	
	@Id
	@ManyToOne
	@JoinColumn(name="APV_CODE")
	private Approval approval;
	
	@ManyToOne
	@JoinColumn(name="EMP_CODE")
	private Employee employee;

}
