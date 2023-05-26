package com.groovy.ware.attendance.dto;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDateTime;
import java.time.LocalTime;

import com.groovy.ware.employee.dto.EmployeeDto;

import lombok.Data;

@Data
public class AttendanceDto {
    
    private Long attCode;
    private EmployeeDto employee;
    private Time attStart;
    private Time attEnd;
    private String attDiv;
    private Date attDate;
    private String attLate;

}
