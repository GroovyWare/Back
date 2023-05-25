package com.groovy.ware.member.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="GRV_EMPLOYEE")
public class MemberEmployee {

	@Id
	@Column(name="EMP_CODE")
	private Long empCode;
	
	@Column(name="EMP_NAME")
	private String empName;
	

}