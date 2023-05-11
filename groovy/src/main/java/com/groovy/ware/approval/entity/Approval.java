package com.groovy.ware.approval.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;

import com.groovy.ware.document.Entity.Document;
import com.groovy.ware.employee.entity.Employee;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="GRV_APPROAL")
@SequenceGenerator(name="APPROVAL_SEQ_GENERATOR", sequenceName="SEQ_APV_CODE", initialValue=1, allocationSize=1)
@DynamicInsert
public class Approval {
	
	@Id
	@Column(name="APV_CODE")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="APPROVAL_SEQ_GENETRATOR")
	private Long apvCode;
	
	@Column(name="APV_CREATED_DATE")
	private Date apvCreatedDate;
	
	@Column(name="APV_STATUS")
	private String apvStatus;
	
	@Column(name="APV_END_DATE")
	private Date apvEndDate;
	
	@OneToMany
	@JoinColumn(name="EMP_CODE")
	private Employee employee;
	
	@OneToMany
	@JoinColumn(name="DOC_CODE")
	private Document document;
	
	@Column(name="APV_CONTEXT")
	private String apvContext;
	
	

}
