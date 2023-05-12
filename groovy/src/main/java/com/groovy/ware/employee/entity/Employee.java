package com.groovy.ware.employee.entity;

import java.util.Date;

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

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="GRV_EMPLOYEE")
@SequenceGenerator(name="EMPLOYEE_SEQ_GENERATOR", sequenceName="SEQ_EMP_CODE", initialValue=1, allocationSize=1)
@DynamicInsert // 엔티티 내부의 null 값의 경우 구문을 생성할 때 제외한다. => Oracle DB에 설정된 data default 값을 사용할 수 있다.
public class Employee {


	@Id
	@Column(name="EMP_CODE")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="EMPLOYEE_SEQ_GENERATOR")
	private Long empCode;
	@Column(name="EMP_ID")
	private String empId;
	@Column(name="EMP_PASSWORD")
	private String empPassword;
	@Column(name="EMP_NAME")
	private String empName;
	@Column(name="EMP_PHONE")
	private String empPhone;
	@Column(name="EMP_EMAIL")
	private String empEmail;
	@Column(name="EMP_ADDRESS")
	private String empAddress;
	@Column(name="ENT_DATE")
	private Date entDate;
	@Column(name="EX_DATE")
	private Date exDate;
	@Column(name="EMP_STATUS")
	private String EmpStatus;
//	@ManyToOne
//	@JoinColumn(name="DEPT_CODE")
//	private Department dept;
//	@ManyToOne
//	@JoinColumn(name="POSITION_CODE")
//	private Position position;
	
	
	
}
	@Column(name="EMP_ENT_DATE")
	private Date entDate;
	@Column(name="EMP_EX_DATE")
	private Date exDate;
	@Column(name="EMP_ENTIRE")
	private String empEnitre;
	@ManyToOne
	@JoinColumn(name="DEPT_CODE")
	private Department dept;
	@ManyToOne
	@JoinColumn(name="POSITION_CODE")
	private Position position;
	
	
	
}
