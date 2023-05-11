package com.groovy.ware.employee.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Table(name="GRV_POSITION")
@SequenceGenerator(name="POSITION_SEQ_GENERATOR", sequenceName="SEQ_POS_CODE", initialValue=1, allocationSize=1)
public class Position {

	@Id
	@Column(name="POSITION_CODE")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="POSITION_SEQ_GENERATOR")
	private long positionCode;
	
	@Column(name="POSITION_NAME")
	private String positionName;
	
}
