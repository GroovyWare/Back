package com.groovy.ware.approveLine.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
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
@Table(name="GRV_APPROVELINE")
public class ApproveLine implements Serializable{
	
	@Id
	@ManyToOne
	@JoinColumn(name="APV_CODE")
	private Approval approval;
	
	@ManyToOne
	@JoinColumn(name="EMP_CODE")
	private Employee employee;
	
	@Column(name="APL_NUM")
	private String aplNum;
	
	@Column(name="APL_STATUS")
	private String aplStatus;
	
	@Column(name="APL_DATE")
	private Date aplDate;
	
	@Column(name="APL_YN")
	private String aplYn;

}
