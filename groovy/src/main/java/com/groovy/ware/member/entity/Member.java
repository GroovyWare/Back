package com.groovy.ware.member.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="GRV_MEMBER")
@DynamicInsert
public class Member {
	
	@Id
	@Column(name="MEM_CODE")
	private Long memCode;
	
	@Column(name="MEM_NAME")
	private String memName;
	
	@Column(name="MEM_PHONE")
	private String memPhone;

	@Column(name="MEM_DELETE_DATE")
	private Date memDeleteDate;
	
	@Column(name="MEM_START_DATE")
	private Date memStartDate;
	
	@Column(name="MEM_END_DATE")
	private Date memEndDate;
	
	

	

}
