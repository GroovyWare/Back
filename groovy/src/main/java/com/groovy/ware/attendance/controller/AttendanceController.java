package com.groovy.ware.attendance.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.groovy.ware.attendance.dto.AttendanceDto;
import com.groovy.ware.attendance.service.AttendanceService;
import com.groovy.ware.common.dto.ResponseDto;
import com.groovy.ware.employee.dto.EmployeeDto;
import com.groovy.ware.employee.service.EmployeeService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/attendance")
public class AttendanceController {

    private final AttendanceService attendanceService;
    
    public AttendanceController(AttendanceService attendanceService) {
        this.attendanceService = attendanceService;
     
    }

    /* 1. 근태 메인 */

    @GetMapping("/main")
    
    public ResponseEntity<ResponseDto> getMain(@AuthenticationPrincipal EmployeeDto employee){
        log.info("[AttendanceController] start ====================-");

        log.info("[AttendanceController] employee" + employee);

        return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, "근태 조회 완료", attendanceService.viewMain(employee)));
    }

    /* 2. 출근 */
    // @PostMapping("/main/go")

    // public ResponseEntity<ResponseDto> goWork(@RequestParam AttendanceDto attendanceDto, @AuthenticationPrincipal EmployeeDto employee) 
    // {
    //     attendanceDto.setEmployee(employee);
    //     attendanceService.addGoWork(attendanceDto);

    //     return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, "출근되었습니다."));
    // }


    /* 3. 퇴근*/

    
}
