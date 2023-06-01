package com.groovy.ware.calendar.service;

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
   // private final DepartmentRepository deptrepository;

   public CalendarService(CalendarRepository calendarRepository, EmployeeRepository employeeRepository,
         ModelMapper modelMapper) {
      this.calendarRepository = calendarRepository;
      this.employeeRepository = employeeRepository;
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
      log.info("[CalendarService] inserting event start");
      log.info("[CalendarService] calenderDto : {}", calendarDTO); 
      calendarDTO.setSchDiv("휴가");

      calendarRepository.save(modelMapper.map(calendarDTO, Calendar.class));

      log.info("[CalendarService] inserting event end");
   }

   /* 3. 검색한 스케쥴 제목으로 리스트 보여주기 */

   public Page<CalendarDTO> selectScheduleListbyTitle(int page, String schTitle, EmployeeDto writer) {
      Pageable pageable = PageRequest.of(page - 1, 5, Sort.by("id").descending());

      Page<Calendar> scheduleList = calendarRepository.findByTitle(pageable, schTitle, writer.getEmpCode(),
            writer.getDept().getDeptCode());
      Page<CalendarDTO> scheduleDtoList = scheduleList.map(calendar -> modelMapper.map(calendar, CalendarDTO.class));
      log.info("[CalenderService] scheduleList : {}", scheduleList);

      return scheduleDtoList;
   }

   /* 3-1. 상세 일정 보여주기 */

   public CalendarDTO selectScheduleDetail(Long id) {

      CalendarDTO calendarDTO = modelMapper.map(calendarRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("존재하지 않는 스케줄.")), CalendarDTO.class);

      return calendarDTO;
   }

   // /* 4. 개인일정 수정하기 */
   // @Transactional
   // public void modifyCalendar(Long id, CalendarDTO calendarDTO) {
   // log.info("[CalendarService] modify start");
   // log.info("[CalendarService] calendarDto : {}" , calendarDTO);

   // Calendar originCalendar = calendarRepository.findById(calendarDTO.getid())
   // .orElseThrow(()-> new IllegalArgumentException("그런 스케줄은 없습니다. id=" +
   // calendarDTO.getid()));

   // originCalendar.setSchTitle(calendarDTO.getSchTitle());
   // originCalendar.setSchContext(calendarDTO.getSchContext());
   // originCalendar.setSchStart(calendarDTO.getSchStart());
   // originCalendar.setSchEnd(calendarDTO.getSchEnd());

   // log.info("[CalendarService] modify end");
   // }

   /* 4. 개인일정 수정하기 */
   @Transactional
   public void modifyCalendar(CalendarDTO calendarDTO, EmployeeDto writer) {
      log.info("[CalendarService] modify start");
      log.info("[CalendarService] calendarDto : {}", calendarDTO);

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

      log.info("[CalendarService] modify end");
   }

   /* 4-1. 드래그로 수정하기 */
   // @Transactional
   // public void dragCalendar(CalendarDTO calendarDTO, EmployeeDto writer){
   // log.info("[CalendarService] drag start");
   // log.info("[CalendarService] calendarDto : {}", calendarDTO);
   // Calendar originCalendar = calendarRepository.findById(calendarDTO.getId())
   // .orElseThrow(() -> new IllegalArgumentException("그런 스케줄은 없습니다" +
   // calendarDTO.getId()));

   // originCalendar.update(calendarDTO.getTitle(), calendarDTO.getContext(), null,
   // null, calendarDTO.getColor(), calendarDTO.getTextColor());

   // }

   /* 5. 일정 삭제하기 */
   @Transactional
   public void deleteSchedule(EmployeeDto writer, Long id) {
      log.info("[CalendarService] deletestart");

      calendarRepository.deleteById(id);

      log.info("[CalendarService] delete end");
   }





/* 6. 휴가 삽입 */
@Transactional
public void addVacation(CalendarDTO calendarDTO){
   log.info("[CalendarService] inserting vacation event start");
   log.info("[CalendarService] calenderDto : {}", calendarDTO);

   calendarRepository.save(modelMapper.map(calendarDTO, Calendar.class));

   log.info("[CalendarService] vacation inserting event end");
}

// /* 6-1. 휴가의 조건 */
// public CalendarDTO vacationCondition(EmployeeDto writer) {

//    log.info("[CalendarService] condition start ==========================");
//    // Employee employee = employeeRepository.findById(writer.getEmpCode())
//    // .orElseThrow(() -> new UserNotFoundException("없는 사람입니다."));
   
   
//    Calendar calendar2 = calendarRepository.findVacationConditions(writer.getEmpCode())
//    .orElseThrow('')
//    CalendarDTO calendarDto = modelMapper.map(calendar2, CalendarDTO.class);
//    log.info("[CalendarService] condition end ==========================");
   
//    return calendarDto;
// }





}