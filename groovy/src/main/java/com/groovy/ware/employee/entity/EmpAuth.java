package com.groovy.ware.employee.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="GRV_EMP_AUTH")
public class EmpAuth {
	
	@EmbeddedId
	private EmpAuthPK empAuthPK;
	
	@ManyToOne
	@JoinColumn(name="AUTH_CODE", insertable=false, updatable=false)
	private Authority auth;
	
}
