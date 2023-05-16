package com.groovy.ware.history.entity;



import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;

import com.groovy.ware.employee.entity.Employee;
import com.groovy.ware.pass.entity.Pass;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name="GRV_HISTORY")
@DynamicInsert
@SequenceGenerator(name="HIS_SEQ_GENERATOR",
				   sequenceName="SEQ_HIS_CODE",
				   initialValue=1, allocationSize=1)
public class History {
	
	@Id
	@Column(name="RES_HISTORY")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="HIS_SEQ_GENERATOR")
	private Long resHistory;
	
	@Column(name="MEM_CODE")
	private Long memCode;
	
	@ManyToOne
	@JoinColumn(name="PASS_CODE")
	private Pass pass;
	
	@Column(name="RES_START")
	private Date resStart;

	@Column(name="RES_END")
	private Date resEnd;
	
	@ManyToOne
	@JoinColumn(name="EMP_CODE")
	private Employee employee;

}



