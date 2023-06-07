package com.groovy.ware.approval.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;

import com.groovy.ware.employee.entity.Employee;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@IdClass(ApproveLineId.class)
@Table(name="GRV_APPROVELINE")
@DynamicInsert
public class ApproveLine implements Serializable{
	
	@Id
	@Column(name="EMP_CODE", nullable = true)
	private Integer empCode;
		
	@Id
	@Column(name="APV_CODE", nullable = true)
	private Integer apvCode;
	
	@Column(name="APL_NUM")
	private String aplNum;
	
	@Column(name="APL_STATUS")
	private String aplStatus;
	
	@Column(name="APL_DATE")
	private Date aplDate;


	public Integer getId() {
		return empCode;
	}

}
