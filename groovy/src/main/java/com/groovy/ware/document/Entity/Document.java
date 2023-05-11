package com.groovy.ware.document.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
	public Long docCode;
	
	@Column(name="DOC_FILE")
	public String docFile;

}
