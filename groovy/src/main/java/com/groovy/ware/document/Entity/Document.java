package com.groovy.ware.document.Entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="GRV_DOCUMENT")
@SequenceGenerator(name="DOCUMENT_SEQ_GENERATOR", sequenceName="SEQ_DOC_CODE", initialValue=1, allocationSize=1)
public class Document {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="DOCUMENT_SEQ_GENERATOR")
	@Column(name="DOC_CODE")
	public Long docCode;
	
	@Column(name="DOC_CUSTOM")
	private String docCustom;
	
	@Column(name="DOC_TITLE")
	private String docTitle;
	
	@Column(name="DOC_CONTEXT")
	private String docContext;
	
	@Column(name="DOC_START_DATE")
	private Date docStartDate;
	
	@Column(name="DOC_END_DATE")
	private Date docEndDate;
}
