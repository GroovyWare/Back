package com.groovy.ware.calendar.service;

import java.util.List;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.groovy.ware.calendar.dto.CalendarDTO;
import com.groovy.ware.calendar.entity.Calendar;
import com.groovy.ware.calendar.repository.CalendarRepository;
import com.groovy.ware.member.repository.MemberRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CalendarService {
   
   private final CalendarRepository calendarRepository;
   private final ModelMapper modelMapper;
   private final MemberRepository memberRepository;
   // private final DepartmentRepository deptrepository;

   public CalendarService(CalendarRepository calendarRepository, MemberRepository memberRepository, ModelMapper modelMapper)
   {
       this.calendarRepository = calendarRepository;
       this.memberRepository = memberRepository;
      //  this.deptrepository = deptrepository;
       this.modelMapper = modelMapper;
   }


   
   /* 1. 캘린더 메인에서 전체일정 보여주기  직원값을 가져와야한다.*/


   /* 2. 개인일정 생성하기 */
   @Transactional
   public void addSchedule(CalendarDTO calendarDTO) {
      log.info("[CalendarService] inserting event start");
      log.info("[CalenderService] calenderDto : {}", calendarDTO);

      calendarRepository.save(modelMapper.map(calendarDTO, Calendar.class));


      log.info("[CalendarService] inserting event end");
   }


   /* 3. 검색한 스케쥴 제목으로 리스트 보여주기 */
 
   public Page<CalendarDTO> selectScheduleListbyTitle(int page, String schTitle) {
      Pageable pageable = PageRequest.of(page - 1, 5, Sort.by("schCode").descending());

      Page<Calendar> scheduleList = calendarRepository.findBySchTitle(pageable, schTitle);
      Page<CalendarDTO> scheduleDtoList = scheduleList.map(calendar -> modelMapper.map(calendar, CalendarDTO.class));
      log.info("[CalenderService] scheduleList : {}", scheduleList);
      
      return scheduleDtoList;
   }





   /* 3-1. 상세 일정 보여주기 */

   public CalendarDTO selectOneSchedule(Long schCode) {


      Calendar calendar = calendarRepository.findBySchCode(schCode)
      .orElseThrow(() -> new IllegalArgumentException("해당 코드가 없습니다. schCode=" + schCode));

      CalendarDTO calendarDTO = modelMapper.map(calendar, CalendarDTO.class);
      

      return calendarDTO;
  }

   /* 3. 개인일정 수정하기 */

   /* 4. 일정 삭제하기 */














}
