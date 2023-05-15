package com.groovy.ware.employee.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class EmpAuthPK implements Serializable{

	@ManyToOne
	@JoinColumn(name="AUTH_CODE") 
	private Authority auth;
	
	@ManyToOne
	@JoinColumn(name="EMP_CODE")
	private Employee emp;
	
	public EmpAuthPK() {}
	
	public EmpAuthPK(Authority auth, Employee emp) {
		super();
		this.auth = auth;
		this.emp = emp;
	}

	public Authority getAuth() {
		return auth;
	}

	public void setAuth(Authority auth) {
		this.auth = auth;
	}

	public Employee getEmp() {
		return emp;
	}

	public void setEmp(Employee emp) {
		this.emp = emp;
	}
	
	
	
	
	
}
