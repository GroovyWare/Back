package com.groovy.ware.approval.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.groovy.ware.employee.entity.Employee;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@IdClass(ReaderId.class)
@Table(name="GRV_READER")
public class Reader implements Serializable{
	
	@Id
	private Integer apvCode;
	
	@Id
	private Integer empCode;

}
