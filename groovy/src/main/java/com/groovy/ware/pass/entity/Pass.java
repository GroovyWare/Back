package com.groovy.ware.pass.entity;

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
@Table(name="GRV_PASS")
public class Pass {
	
	@Id
	@Column(name="PASS_CODE")
	private Long passCode;
	
	@Column(name="PASS_TYPE")
	private String passType;

	@Column(name="PASS_PRICE")
	private Long passPrice;
	
	@Column(name="PASS_AMOUNT")
	private Long passAmount;
	
	@Column(name="PASS_ETC")
	private String passEtc;

}
