package com.groovy.ware.calendar.entity;

import java.util.Date;

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

import org.hibernate.annotations.DynamicInsert;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name="GRV_SCHEDULE")
@SequenceGenerator(
    name = "SCHEDULE_CODE_GENERATOR"
,   sequenceName = "SEQ_SCH_CODE"
,   initialValue = 1, allocationSize = 1
)
@DynamicInsert
public class Calendar {

    @Id
    @Column(name = "SCH_CODE")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SCHEDULE_CODE_GENERATOR")
    private Long schCode;


    @Column(name = "SCH_TITLE")
    private String schTitle;

    @Column(name = "SCH_CONTEXT")
    private String schContext;

    @Column(name = "SCH_DIV")
    private String schDiv;

    @ManyToOne(fetch = FetchType.LAZY)
    @Column(name = "DEPT_CODE")
    private Dept dept;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "EMP_CODE")
    @Column(name = "EMP_CODE")
    private Employee employee;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "EMP_CODE")
    @Column(name = "SCH_WRITER")
    private Employee schwriter;

    @Column(name = "SCH_START")
    private Date schStart;

    @Column(name = "SCH_END")
    private Date schEnd;

    
}
