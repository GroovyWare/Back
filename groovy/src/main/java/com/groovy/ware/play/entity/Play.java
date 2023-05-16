package com.groovy.ware.play.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.groovy.ware.employee.entity.Employee;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="GRV_PLAY")
@SequenceGenerator(name="PLAY_SEQUENCE_GENERATOR", sequenceName="SEQ_PLAY_CODE", initialValue = 1, allocationSize = 1)
public class Play {
	
	@Id
	@Column(name="PLY_CODE")
	private Long plyCode;
	
	@ManyToOne
	@JoinColumn(name="EMP_CODE")
	private Employee employee;
	
	@Column(name="PLY_TYPE")
	private String plyType;
	
	@Column(name="PLY_TIME")
	private Date plyTime;
	
	@Column(name="PLY_PLAY")
	private String plyPlay;
	
	@Column(name="PLY_WEIGHT")
	private String plyWeight;
	
	@Column(name="PLY_NUM")
	private Integer plyNum;

}
