package com.groovy.ware.attendance.dto;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.LocalTime;

import com.groovy.ware.employee.dto.EmployeeDto;

import lombok.Data;

@Data
public class AttendanceDto {
    
    private Long attCode;
    private EmployeeDto employee;
    private LocalTime attStart;
    private LocalTime attEnd;
    private String AttDiv;
    private Date AttDate;
    private String AttLate;

}
