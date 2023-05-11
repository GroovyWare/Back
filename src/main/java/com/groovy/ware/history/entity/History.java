package com.groovy.ware.history.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;

import com.groovy.ware.member.entity.Member;
import com.groovy.ware.pass.entity.Pass;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name="GRV_HISTORY")
@DynamicInsert
public class History {
	
	@Id
	@Column(name="RES_HISTORY")
	private String resHistory;
	
	@ManyToOne
	@JoinColumn(name="MEM_CODE")
	private Member memCode;
	
	@ManyToOne
	@JoinColumn(name="PASS_CODE")
	private Pass passCode;
	
	@Column(name="RES_START")
	private Date resStart;

	@Column(name="RES_END")
	private Date resEnd;

}
