package com.groovy.ware.employee.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name="GRV_EMP_AUTH")
@SequenceGenerator(name="EMP_AUTH_SEQ_GENERATOR", sequenceName="SEQ_EMP_AUTH_CODE", initialValue=1, allocationSize=1)
public class EmpAuth {
	
	@Id
	@Column(name="EMP_AUTH_CODE")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="EMP_AUTH_SEQ_GENERATOR")
	private Long empAuthCode;
	
	@Column(name="EMP_CODE")	
	private Long empCode;
	
	@ManyToOne
	@JoinColumn(name="AUTH_CODE")
	private Authority auth;
	
}