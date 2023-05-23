package com.groovy.ware.calendar.dto;

import java.sql.Timestamp;


import com.groovy.ware.employee.dto.DepartmentDto;
import com.groovy.ware.employee.dto.EmployeeDto;

import lombok.Data;

@Data
public class CalendarDTO {
    
    private Long schCode;

    private Long id;
    private String title;
    private String context;
    private String schDiv;
    private DepartmentDto dept;
    private EmployeeDto schWriter;
    private Timestamp Start;
    private Timestamp End;
    private String textColor;
    private String color;


    public void setId() {

        this.id = schCode;
    }

    public Long getId() {
        return schCode;
    };

}
