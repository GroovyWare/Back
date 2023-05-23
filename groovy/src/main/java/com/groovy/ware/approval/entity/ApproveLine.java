package com.groovy.ware.approval.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.groovy.ware.employee.entity.Employee;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="GRV_APPROVELINE")
public class ApproveLine implements Serializable{
	
	@EmbeddedId
	private ApproveLineId approveLineId;
	
	@Column(name="APL_NUM")
	private String aplNum;
	
	@Column(name="APL_STATUS")
	private String aplStatus;
	
	@Column(name="APL_DATE")
	private Date aplDate;
	
	@Column(name="APL_YN")
	private String aplYn;
	
}
