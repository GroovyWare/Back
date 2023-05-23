package com.groovy.ware.member.entity;


import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;

import com.groovy.ware.history.entity.History;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@Table(name="GRV_MEMBER")
@DynamicInsert
@SequenceGenerator(name="MEM_SEQ_GENERATOR",
				   sequenceName="SEQ_MEM_CODE",
				   initialValue=1, allocationSize=1)
public class Member {
	
	@Id
	@Column(name="MEM_CODE")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="MEM_SEQ_GENERATOR")
	private Long memCode;
	
	@Column(name="MEM_NAME")
	private String memName;
	
	@Column(name="MEM_PHONE")
	private String memPhone;

	@Column(name="MEM_DELETE_DATE")
	private Date memDeleteDate;
	
	@Column(name="MEM_START_DATE")
	private Date memStartDate;
	
	@Column(name="MEM_END_DATE")
	private Date memEndDate;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="MEM_CODE")
	private List<History> history;
	
	
	/* Member entity 수정 용도의 메소드 */
	public void modify(String memName, String memPhone, Date memDeleteDate, Date memStartDate,
			Date memEndDate, List<History> history) {
		
		this.memName = memName;
		this.memPhone = memPhone;
		this.memDeleteDate = memDeleteDate;
		this.memStartDate = memStartDate;
		this.memEndDate= memEndDate;
		this.history = history;
		
	}
	
	
	
	


}