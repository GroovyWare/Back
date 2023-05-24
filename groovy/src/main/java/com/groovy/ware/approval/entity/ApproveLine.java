package com.groovy.ware.approval.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.persistence.Version;

import org.hibernate.annotations.DynamicInsert;

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
	public Integer empCode;
		
	@Id
	@Column(name="APV_CODE", nullable = true)
	public Integer apvCode;
	
	@Column(name="APL_NUM")
	private String aplNum;
	
	@Column(name="APL_STATUS")
	private String aplStatus;
	
	@Column(name="APL_DATE")
	private Date aplDate;
	
	@Column(name="APL_YN")
	private String aplYn;

}
