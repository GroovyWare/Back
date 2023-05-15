package com.groovy.ware.employee.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="GRV_AUTHORITY")
@SequenceGenerator(name="AUTHORITY_SEQ_GENERATOR", sequenceName="SEQ_AUTH_CODE", initialValue=1, allocationSize=1)
public class Authority {

	@Id
	@Column(name="AUTH_CODE")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="AUTHORITY_SEQ_GENERATOR")
	private Long authCode;
	@Column(name="AUTH_NAME")
	private String authName;
}
