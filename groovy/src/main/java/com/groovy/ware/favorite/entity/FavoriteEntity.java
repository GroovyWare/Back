package com.groovy.ware.favorite.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.groovy.ware.document.Entity.Document;
import com.groovy.ware.employee.entity.Employee;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="GRV_FAVORITE")
public class FavoriteEntity {
	
	@ManyToOne
	@JoinColumn(name="EMP_CODE")
	private Employee employee;
	
	@ManyToOne
	@JoinColumn(name="DOC_CODE")
	private Document document;

}
