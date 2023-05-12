package com.groovy.ware.common.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.groovy.ware.employee.entity.Employee;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name="GRV_FILE")
@SequenceGenerator(name="FILE_SEQ_GENERATOR", sequenceName="SEQ_FILE_CODE", initialValue=1, allocationSize=1)

public class File {

	@Id
	@Column(name="FILE_CODE")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="FILE_SEQ_GENERATOR")
	private Long fileCode;
	@Column(name="FILE_DIV")
	private String fileDiv;
//	@Column(name="EMP_CODE")
//	private Long empCode;
	@Column(name="ANN_CODE")
	private Long annCode;
	@Column(name="FILE_ORIGINAL_NAME")
	private String fileOriginalName;
	@Column(name="FILE_SAVED_NAME")
	private String fileSavedName;
	
	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name="EMP_CODE")
	private Employee employee;
	
//	@OneToOne(cascade = CascadeType.PERSIST, mappedBy="file")
//	private Employee employee;
	

	
}
