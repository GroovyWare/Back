package com.groovy.ware.employee.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Table(name="GRV_DEPARTMENT")
@SequenceGenerator(name="DEPARTMENT_SEQ_GENERATOR", sequenceName="SEQ_DEPT_CODE", initialValue=1, allocationSize=1)
public class Department {

	@Id
	@Column(name="DEPT_CODE")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="DEPARTMENT_SEQ_GENERATOR")
	private long deptCode;
	
	@Column(name="DEPT_TITLE")
	private String deptTitle;
}
