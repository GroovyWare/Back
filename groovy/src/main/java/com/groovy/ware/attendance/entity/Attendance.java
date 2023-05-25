package com.groovy.ware.attendance.entity;

import java.sql.Date;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.DynamicInsert;

import com.groovy.ware.employee.entity.Employee;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="GRV_ATTENDENCE")
@SequenceGenerator(name="ATTENDANCE_SEQ_GENERATOR", sequenceName = "SEQ_ATT_CODE", initialValue = 1, allocationSize = 1)
@DynamicInsert
public class Attendance {
    
    @Id
    @Column(name = "ATT_CODE")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ATTENDANCE_SEQ_GENERATOR")
    private Long attCode;

    @ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="EMP_CODE")
    private Employee employee;

   
    @Column(name = "ATT_START")
    private LocalTime attStart;

  
    @Column(name = "ATT_END")
    private LocalTime attEnd;

    @Column(name = "ATT_DIV")
    private String AttDiv;

    @Column(name = "ATT_DATE")
    private Date AttDate;

    @Column(name = "ATT_LATE")
    private String AttLate;



}
