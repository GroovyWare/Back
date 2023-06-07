package com.groovy.ware.employee.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.DynamicInsert;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.groovy.ware.common.entity.File;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name="GRV_EMPLOYEE")
@SequenceGenerator(name="EMPLOYEE_SEQ_GENERATOR", sequenceName="SEQ_EMP_CODE", initialValue=1, allocationSize=1)
@DynamicInsert
@JsonIdentityInfo(
		  generator = ObjectIdGenerators.PropertyGenerator.class, 
		  property = "empCode")
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
	@Temporal(TemporalType.DATE)
	@Column(name="EMP_ENT_DATE")
	private Date empEntDate;
	@Temporal(TemporalType.DATE)
	@Column(name="EMP_EX_DATE")
	private Date empExDate;
	@Column(name="EMP_STATUS")
	private String empStatus;
	@ManyToOne
	@JoinColumn(name="DEPT_CODE")
	private Department dept;
	@ManyToOne
	@JoinColumn(name="POSITION_CODE")
	private Position position;
//	@Column(name="VAC_REMAIN")
//	private Long vacRemain;

	@OneToOne(mappedBy="employee")
	private File file;
	
	@OneToMany(cascade = CascadeType.PERSIST)
	@JoinColumn(name="EMP_CODE")
	private List<EmpAuth> auths;
	
	public void update(String empName, String empPhone, String empEmail, String empAddress, Date empEntDate, Date empExDate,
			Department dept, Position position, File file, List<EmpAuth> auths) {
		this.empName = empName;
		this.empPhone = empPhone;
		this.empEmail = empEmail;
		this.empAddress = empAddress;
		this.empEntDate = empEntDate;
		this.empExDate = empExDate;
		this.dept = dept;
		this.position = position;
		this.file = file;
		this.auths = auths;
	}

}