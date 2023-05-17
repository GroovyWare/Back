package com.groovy.ware.employee.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;


@Embeddable
public class EmpAuthPK implements Serializable{

	@Column(name="EMP_CODE")
	private Long empCode;
	
	@Column(name="AUTH_CODE")
	private Long authCode;
		
	public EmpAuthPK() {}

	public EmpAuthPK(Long empCode, Long authCode) {
		super();
		this.empCode = empCode;
		this.authCode = authCode;
	}


	public Long getEmpCode() {
		return empCode;
	}


	public void setEmpCode(Long empCode) {
		this.empCode = empCode;
	}


	public Long getAuthCode() {
		return authCode;
	}


	public void setAuthCode(Long authCode) {
		this.authCode = authCode;
	}


	@Override
	public String toString() {
		return "EmpAuthPK [empCode=" + empCode + ", authCode=" + authCode + "]";
	}


	
	
	
	
	
}
