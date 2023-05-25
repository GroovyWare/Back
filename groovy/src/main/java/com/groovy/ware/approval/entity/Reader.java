package com.groovy.ware.approval.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@IdClass(ReaderId.class)
@Table(name="GRV_READER")
public class Reader implements Serializable{
	
	@Id
	@Column(name="EMP_CODE", nullable = true)
	public Integer empCode;
		
	@Id
	@Column(name="APV_CODE", nullable = true)
	public Integer apvCode;

}
