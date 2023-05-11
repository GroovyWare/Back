package com.groovy.ware.reader.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.groovy.ware.approval.entity.Approval;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="GRV_READER")
public class ReaderEntity {
	
	@ManyToOne
	@JoinColumn(name="APV_CODE")
	private Approval approval;
	
	@ManyToOne
	@JoinColumn(name="EMP_CODE")
	private Employee employee;

}
