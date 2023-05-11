package com.groovy.ware.calendar.dto;

import java.util.Date;

import com.groovy.ware.employee.dto.DepartmentDto;
import com.groovy.ware.employee.dto.EmployeeDto;

import lombok.Data;

@Data
public class CalendarDTO {
    
    private Long schCode;
    private String schTitle;
    private String schContext;
    private String schDiv;
    private DepartmentDto dept;
    private EmployeeDto employee;
    private EmployeeDto schwriter;
    private Date schStart;
    private Date schend;



}
