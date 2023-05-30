package com.groovy.ware.attendance.service;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.groovy.ware.attendance.dto.AttendanceDto;
import com.groovy.ware.attendance.entity.Attendance;
import com.groovy.ware.attendance.repository.AttendanceRepository;
import com.groovy.ware.employee.dto.EmployeeDto;
import com.groovy.ware.employee.entity.Employee;
import com.groovy.ware.employee.repository.DepartmentRepository;
import com.groovy.ware.employee.repository.EmployeeRepository;
import com.groovy.ware.employee.service.EmployeeService;

import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AttendanceService {


    private final EmployeeRepository employeeRepository;
    private final AttendanceRepository attendanceRepository;
    private final ModelMapper modelMapper;
    private final DepartmentRepository departmentRepository;

    public AttendanceService(EmployeeRepository employeeRepository, AttendanceRepository attendanceRepository, ModelMapper modelMapper, DepartmentRepository departmentRepository){
        this.employeeRepository = employeeRepository;
        this.attendanceRepository = attendanceRepository;
        this.modelMapper = modelMapper;
        this.departmentRepository = departmentRepository;
    }
    


    /* 개인 출근 퇴근 시간의 조회(메인에서 기본적으로 띄워준다) */
    public AttendanceDto viewMain(EmployeeDto employeeDto) {

        log.info("[AttendanceService] start==============");
        log.info("[AttendanceService] employeeDto : {}", employeeDto);

        Attendance attendance = attendanceRepository.findOneAttendance(employeeDto.getEmpCode());
        AttendanceDto attendanceDto = modelMapper.map(attendance, AttendanceDto.class);


        return attendanceDto;
    }

    /* 출근 버튼을 누를때 */
    @Transactional
    public void addGoWork(AttendanceDto attendanceDto) {
        log.info("[AttendanceService] start==============");
        log.info("[AttendanceService] attendanceDto : {}", attendanceDto);
        attendanceDto.setAttDate(new Date(System.currentTimeMillis()));
        attendanceRepository.save(modelMapper.map(attendanceDto, Attendance.class));


    }
    

    /* 퇴근 버튼을 누를때 (수정) */
    @Transactional
    public void leaveWork(AttendanceDto attendanceDto, EmployeeDto employee) {
        log.info("[AttendanceService] update start ==============");

        // Attendance originAttendance = attendanceRepository.findOneAttendance(employee.getEmpCode());
        Attendance originAttendance = attendanceRepository.findById(attendanceDto.getAttCode()).orElseThrow(() -> new IllegalArgumentException("그런 출근기록은 없습니다." + attendanceDto.getAttCode()));

        originAttendance.setAttEnd(new Time(System.currentTimeMillis()));
        
       
        
        

        log.info("[AttendanceService] update end ==============");


    }

}
