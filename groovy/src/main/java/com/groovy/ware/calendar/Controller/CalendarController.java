package com.groovy.ware.calendar.Controller;

import org.apache.coyote.Response;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
// import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.groovy.ware.calendar.dto.CalendarDTO;
import com.groovy.ware.calendar.service.CalendarService;
import com.groovy.ware.common.ResponseDto;
import com.groovy.ware.common.paging.Pagenation;
import com.groovy.ware.common.paging.PagingButtonInfo;
import com.groovy.ware.common.paging.ResponseDtoWithPaging;
import com.groovy.ware.employee.dto.EmployeeDto;
import com.groovy.ware.employee.entity.Employee;
import com.groovy.ware.employee.service.EmployeeService;
import com.groovy.ware.member.dto.MemberDto;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/groovy")
public class CalendarController {

    private final EmployeeService empservice;
    private final CalendarService calendarService;

    public CalendarController(CalendarService calendarService) {
        this.calendarService = calendarService;

    }

    /* 1. 캘린더 메인 */
    @GetMapping("/schedules/{empCode}")
    public ResponseEntity<ResponseDto> getAllSchedules(@PathVariable Long empCode) {
        EmployeeDto employeeDto = employeeService.getEmployeeByEmpCode(empCode);
        CalendarDTO scheduleDto = calendarService.viewAllSchedule(employeeDto);
        ResponseDto responseDto = new ResponseDto(true, scheduleDto, "일정 조회 성공");
        return ResponseEntity.ok().body(responseDto);
    }

    /* 2. 일정추가 */
    @PostMapping("/schedule")
    public ResponseEntity<ResponseDto> addingSchedule(@RequestBody CalendarDTO calendarDto, EmployeeDto writer) {

        calendarDto.setSchWriter(writer);
        calendarService.addSchedule(calendarDto);

        return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, "삽입되었습니다."));

    }

    /* 3. 일정 검색시 조회 */
    @GetMapping("/schedule/list")
    public ResponseEntity<ResponseDto> selectScheduleListbyTitle(
            @RequestParam(name = "list") String schTitle,
            @RequestParam(name = "page", defaultValue = "1") int page) {
        log.info("[CalendarController] start ============================");
        log.info("[CalendarController] schedule " + schTitle);
        log.info("[CalendarController] page" + page);

        Page<CalendarDTO> scheduleList = calendarService.selectScheduleListbyTitle(page, schTitle);

        PagingButtonInfo pageInfo = Pagenation.getPagingButtonInfo(scheduleList);

        log.info("[CalendarController] : pageInfo : {}", pageInfo);

        ResponseDtoWithPaging responseDtoWithPaging = new ResponseDtoWithPaging();
        responseDtoWithPaging.setPageInfo(pageInfo);
        responseDtoWithPaging.setData(scheduleList.getContent());

        log.info("[CalendarController] end ============================");
        return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, "조회 성공", responseDtoWithPaging));
    }

    /* 3-1 . 일정 상세를 보여주기 */
    @GetMapping("/schedule/{schCode}/")
    public ResponseEntity<ResponseDto> selectScheduleDetail(@PathVariable Long schCode) {

        return ResponseEntity
                .ok().body(new ResponseDto(HttpStatus.OK, "상세 조회 성공", calendarService.selectOneSchedule(schCode)));

    }

    /* 4. 기본적으로 존재하는 리스트 보여주기 */

    /* 5. 일정 상세 */

}
