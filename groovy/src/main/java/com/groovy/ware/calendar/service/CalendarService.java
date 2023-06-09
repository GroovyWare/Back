package com.groovy.ware.calendar.service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.naming.AuthenticationException;
import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;

import com.groovy.ware.approval.dto.ApprovalDto;
import com.groovy.ware.approval.entity.Approval;
import com.groovy.ware.approval.repository.ApprovalRepository;
import com.groovy.ware.calendar.dto.CalendarDTO;
import com.groovy.ware.calendar.entity.Calendar;
import com.groovy.ware.calendar.repository.CalendarRepository;
import com.groovy.ware.common.exception.UserNotFoundException;
import com.groovy.ware.employee.dto.EmployeeDto;
import com.groovy.ware.employee.entity.Employee;
import com.groovy.ware.employee.repository.EmployeeRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CalendarService {

   private final CalendarRepository calendarRepository;
   private final ModelMapper modelMapper;
   private final EmployeeRepository employeeRepository;
   private final ApprovalRepository approvalRepository;
   // private final DepartmentRepository deptrepository;

   public CalendarService(CalendarRepository calendarRepository, EmployeeRepository employeeRepository, ApprovalRepository approvalRepository,
         ModelMapper modelMapper) {
      this.calendarRepository = calendarRepository;
      this.employeeRepository = employeeRepository;
      this.approvalRepository = approvalRepository;
      // this.deptrepository = deptrepository;
      this.modelMapper = modelMapper;
   }

   /* 1. 캘린더 메인에서 전체일정 보여주기 직원값을 가져와야한다. */
   public List<CalendarDTO> viewAllSchedule(EmployeeDto writer) {
      // Employee employee = employeeRepository.findById(writer.getEmpCode())
      // .orElseThrow(() -> new UserNotFoundException("직원이 없습니다!"));

      List<Calendar> empSchedules = calendarRepository.findByAllSchedulesWithEmpCode(writer.getEmpCode(),
            writer.getDept().getDeptCode());
      List<CalendarDTO> calendarDTOList = empSchedules.stream()
            .map(calendar -> modelMapper.map(calendar, CalendarDTO.class))
            .collect(Collectors.toList());

      return calendarDTOList;
   }

   /* 2. 일정 생성하기 */
   @Transactional
   public void addSchedule(CalendarDTO calendarDTO) {
   
     

      calendarRepository.save(modelMapper.map(calendarDTO, Calendar.class));

     
   }

   /* 3. 검색한 스케쥴 제목으로 리스트 보여주기 */

   public Page<CalendarDTO> selectScheduleListbyTitle(int page, String schTitle, EmployeeDto writer) {
      Pageable pageable = PageRequest.of(page - 1, 5, Sort.by("id").descending());

      Page<Calendar> scheduleList = calendarRepository.findByTitle(pageable, schTitle, writer.getEmpCode(),
            writer.getDept().getDeptCode());
      Page<CalendarDTO> scheduleDtoList = scheduleList.map(calendar -> modelMapper.map(calendar, CalendarDTO.class));
 

      return scheduleDtoList;
   }

   /* 3-1. 상세 일정 보여주기 */

   public CalendarDTO selectScheduleDetail(Long id) {

      CalendarDTO calendarDTO = modelMapper.map(calendarRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("존재하지 않는 스케줄.")), CalendarDTO.class);

      return calendarDTO;
   }



   /* 4. 개인일정 수정하기 */
   @Transactional
   public void modifyCalendar(CalendarDTO calendarDTO, EmployeeDto writer) {
    

      Calendar originCalendar = calendarRepository.findById(calendarDTO.getId())
            .orElseThrow(() -> new IllegalArgumentException("그런 스케줄은 없습니다" + calendarDTO.getId()));

      if (originCalendar.getSchWriter().getEmpCode() == writer.getEmpCode()) {

         originCalendar.setStart(calendarDTO.getStart());
         originCalendar.setEnd(calendarDTO.getEnd());
         originCalendar.setTitle(calendarDTO.getTitle());
         originCalendar.setContext(calendarDTO.getContext());
         originCalendar.setColor(calendarDTO.getColor());
         originCalendar.setTextColor(calendarDTO.getTextColor());
         originCalendar.setSchDiv(calendarDTO.getSchDiv());

      } else {
         throw new RuntimeException("수정할 권한이 없습니다.");
      }

  
   }

  
   /* 5. 일정 삭제하기 */
   @Transactional
   public void deleteSchedule(EmployeeDto writer, Long id) {
      

      calendarRepository.deleteById(id);

    
   }





/* 6. 휴가 삽입(캘린더 메인으로 가면서 변경 = get method와 postmethod는 겹치지 않기 때문에 가능하다.) */
// ...

// ...

@Transactional
public void addVacation(CalendarDTO calendarDTO, ApprovalDto approvalDto, EmployeeDto writer) {


   Approval approval = approvalRepository.findByApvCode(approvalDto.getApvCode());

 
      
      

      Date vacStartDate = approvalDto.getVacStartDate();
      Date vacEndDate = approvalDto.getVacEndDate();
      long startTimestamp = vacStartDate.getTime();
      long endTimestamp = vacEndDate.getTime();
      
      calendarDTO.setTitle("휴가");
      calendarDTO.setContext("휴가입니다.");
      calendarDTO.setDept(writer.getDept());
      // 
 
      calendarDTO.setStart(new Timestamp(startTimestamp));
      calendarDTO.setEnd(new Timestamp(endTimestamp));

      calendarRepository.save(modelMapper.map(calendarDTO, Calendar.class));

}



}