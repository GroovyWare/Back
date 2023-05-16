package com.groovy.ware.calendar.entity;

import java.sql.Timestamp;
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

import com.groovy.ware.employee.entity.Department;

import com.groovy.ware.employee.entity.Employee;

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
    @JoinColumn(name = "DEPT_CODE")
    private Department dept;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SCH_WRITER")
    private Employee schWriter;

    @Column(name = "SCH_START")
    private Timestamp schStart;

    @Column(name = "SCH_END")
    private Timestamp schEnd;

    public void update(String schTitle2, String schContext2, Timestamp schStart2, Timestamp schend2) {
    }

    
}

/* 수정용도 메소드는 별도 정의가 필요하다. */
