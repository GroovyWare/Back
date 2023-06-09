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
import org.springframework.format.annotation.DateTimeFormat;

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
    private Long id;

    @Column(name = "SCH_TITLE")
    private String title;

    @Column(name = "SCH_CONTEXT")
    private String context;

    @Column(name = "SCH_DIV")
    private String schDiv;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DEPT_CODE")
    private Department dept;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SCH_WRITER")
    private Employee schWriter;

    @Column(name = "SCH_START")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    private Timestamp start;

    @Column(name = "SCH_END")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    private Timestamp end;

    @Column(name = "SCH_EVENT_BACK")
    private String color;

    @Column(name = "SCH_EVENT_TEXT")
    private String textColor;
    /* 수정용도 메소드는 별도 정의가 필요하다. */
public void update(String title, String context, Timestamp start, Timestamp end, String color, String textColor) {
    this.title = title;
    this.context = context;
    this.start = start;
    this.end = end;
    this.color = color;
    this.textColor = textColor;

}


public Calendar(String title, String context, String schDiv, Department dept, Employee schWriter, Timestamp start, Timestamp end, String color, String textColor) {
    this.title = title;
    this.context = context;
    this.schDiv = schDiv;
    this.dept = dept;
    this.schWriter = schWriter;
    this.start = start;
    this.end = end;
   this.color = color;
    this.textColor = textColor;
}


public Calendar() {}


}


