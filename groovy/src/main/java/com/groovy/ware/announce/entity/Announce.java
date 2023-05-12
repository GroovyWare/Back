package com.groovy.ware.announce.entity;

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

import com.groovy.ware.common.entity.File;
import com.groovy.ware.employee.entity.Employee;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="GRV_ANNOUNCE")
@SequenceGenerator(name="ANNOUNCE_SEQ_GENERATOR", sequenceName="SEQ_ANN_CODE", initialValue=1, allocationSize=1)
public class Announce {
	
	@Id
	@Column(name="ANN_CODE")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ANNOUNCE_SEQ_GENERATOR")
	private Long annCode;			// 공지사항 코드
	
	@Column(name="ANN_TITLE")
	private String annTitle;		// 제목
	
	@Column(name="ANN_DATE")
	private Date annDate;			// 작성일
	
	@ManyToOne
	@JoinColumn(name="EMP_CODE")
	private Employee empCode;		// 직원 코드
	
	@Column(name="ANN_CONTENT")
	private String annContent;		// 내용
	
	@Column(name="ANN_DELDATE")
	private Date annDelDate;		// 삭제 일자
	
	@Column(name="ANN_DELETE")
	private String annDelete;		// 삭제 여부
	
	@ManyToOne
	@JoinColumn(name="FILE_ORIGINAL_NAME")
	private File fileOriginalName;	// 원본 파일명
	
	@ManyToOne
	@JoinColumn(name="FILE_SAVED_NAME")
	private File fileSavedName;		// 파일 저장명
	
	public void update(String annTitle, String annContent) {
		this.annTitle = annTitle;
		this.annContent = annContent;
	}

}
