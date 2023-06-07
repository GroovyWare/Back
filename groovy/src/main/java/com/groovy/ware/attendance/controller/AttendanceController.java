package com.groovy.ware.attendance.controller;

import java.sql.Date;
import java.sql.Time;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.groovy.ware.attendance.dto.AttendanceDto;
import com.groovy.ware.attendance.service.AttendanceService;
import com.groovy.ware.common.dto.ResponseDto;
import com.groovy.ware.common.paging.Pagenation;
import com.groovy.ware.common.paging.PagingButtonInfo;
import com.groovy.ware.common.paging.ResponseDtoWithPaging;
import com.groovy.ware.employee.dto.EmployeeDto;

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
  

        return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, "근태 조회 완료", attendanceService.viewMain(employee)));
    }

    /* 2. 출근 */
    @PostMapping("/main/go")

    public ResponseEntity<ResponseDto> goWork(@RequestBody AttendanceDto attendanceDto, @AuthenticationPrincipal EmployeeDto employee) 
    {

      
        attendanceDto.setAttDate(new Date(System.currentTimeMillis()));
        attendanceDto.setEmployee(employee);
        attendanceDto.setAttStart(new Time(System.currentTimeMillis()));
        attendanceService.addGoWork(attendanceDto);
      
        
    
        return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, "출근되었습니다."));
    }


    /* 3. 퇴근*/
    @PutMapping("/main/leave")
    public ResponseEntity<ResponseDto> leaveWork(@ModelAttribute AttendanceDto attendanceDto, @AuthenticationPrincipal EmployeeDto employee) 
    {
    
    
       
    
        attendanceDto.setAttDate(new Date(System.currentTimeMillis()));
        attendanceDto.setAttCode(attendanceDto.getAttCode());
        attendanceDto.setEmployee(employee); 
        
        /* 조회 메소드를 이용해서 출근 시간에 대한 변경을 없앤다. */
        AttendanceDto findStartTime = attendanceService.viewMain(employee);
        attendanceDto.setAttStart(findStartTime.getAttStart());
        attendanceDto.setAttCode(findStartTime.getAttCode());
       
    
        attendanceService.leaveWork(attendanceDto, employee); 
     
        return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, "퇴근되었습니다."));
    }
   


    /* 4. 휴가 */
    @PostMapping("/main/vacation")
    public ResponseEntity<ResponseDto> goVacation(@RequestBody AttendanceDto attendanceDto, @AuthenticationPrincipal EmployeeDto employee)
    {   

        return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, "휴가처리되었습니다."));
    }
    
    
    
    /* 전체 직원 근태 조회 */
    @GetMapping("/list")
    public ResponseEntity<ResponseDto> findAttendanceListAll(@RequestParam(name="page", defaultValue="1") int page){
    	
	
		Page<AttendanceDto> attDtoList = attendanceService.findAttendanceListAll(page);
		PagingButtonInfo pageInfo = Pagenation.getPagingButtonInfo(attDtoList);
		
		
	
		
		ResponseDtoWithPaging responseDtoWithPaging = new ResponseDtoWithPaging();
		responseDtoWithPaging.setPageInfo(pageInfo);
		responseDtoWithPaging.setData(attDtoList.getContent());
    	
		
    	return ResponseEntity.ok()
    			.body(new ResponseDto(HttpStatus.OK, "전체 직원 근태 조회 완료", responseDtoWithPaging));
    }
  
    
    /* 직원 개인 근태 조회 */
    @GetMapping("/detail/{empCode}")
    public ResponseEntity<ResponseDto> findAttendanceDetail(@PathVariable Long empCode){
    	
    	return ResponseEntity.ok()
    			.body(new ResponseDto(HttpStatus.OK, "직원 개인 근태 조회 완료", attendanceService.findAttendanceDetail(empCode)));
    }
    

    
    
    
    

}
