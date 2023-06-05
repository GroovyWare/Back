package com.groovy.ware.equipment.dto;

import java.util.Date;

import com.groovy.ware.employee.dto.EmployeeDto;

import lombok.Data;

@Data
public class EquipmentDto {

    private Long eqpCode;			// 기구코드
    private String eqpTitle;		// 기구명
    private String eqpInspector;	// 점검자
    private Date eqpPurchase;		// 구매일자
    private Date eqpDate;			// 점검일자
    private String eqpStatus;		// 상태
    private EmployeeDto employee;	// 작성잔

}
