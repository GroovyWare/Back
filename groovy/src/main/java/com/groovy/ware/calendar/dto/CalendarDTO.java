package com.groovy.ware.calendar.dto;

import java.util.Date;

import lombok.Data;

@Data
public class CalendarDTO {
    
    private Long schCode;
    private String schTitle;
    private String schContext;
    private String schDiv;
    private DeptDto dept;
    private EmployeeDto employee;
    private EmployeeDto schwriter;
    private Date schStart;
    private Date schend;



}
