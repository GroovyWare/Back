package com.groovy.ware.calendar.Controller;

import java.util.List;

import org.apache.coyote.Response;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
// import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
// import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
import com.groovy.ware.employee.dto.DepartmentDto;
import com.groovy.ware.employee.dto.EmployeeDto;
import com.groovy.ware.employee.entity.Employee;
import com.groovy.ware.employee.service.EmployeeService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/calendar")
public class CalendarController {

    private final EmployeeService empService;
    private final CalendarService calendarService;

    public CalendarController(CalendarService calendarService, EmployeeService empService) {
        this.calendarService = calendarService;
        this.empService = empService;

    }

/* 1. 캘린더 메인 */
@GetMapping("/schedule")

public ResponseEntity<ResponseDto> getAllSchedules(@AuthenticationPrincipal EmployeeDto writer) {
    log.info("[CalendarController] start ============================");
    
    /* 테스트용임 아래 5코드는 추후 지울것 + empDTO 새로 짜서 필요한 정보만 가져오는게 효율며7ㄴ에서 이득 */
    // writer= new EmployeeDto();
    // writer.setEmpCode(1L);
    // DepartmentDto dept = new DepartmentDto();
    // dept.setDeptCode(1L);
    // writer.setDept(dept);
    
    
    log.info("[CalendarController] writer " + writer);

    return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, "조회완료!", calendarService.viewAllSchedule(writer)));
}




    /* 2. 일정추가 */
    @PostMapping("/schedule")
    public ResponseEntity<ResponseDto> addingSchedule(@RequestBody CalendarDTO calendarDto, 
    @AuthenticationPrincipal  EmployeeDto writer) {
        /* 로그인 처리가 완료 될때까지 임시로 부여한다. */
        // writer.setEmpCode(1L);
        calendarDto.setSchWriter(writer);
        calendarService.addSchedule(calendarDto);

        return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, "삽입되었습니다."));

    }

    /* 3. 일정 검색시 조회 */
    @GetMapping("/schedule/list")
    public ResponseEntity<ResponseDto> selectScheduleListbyTitle(
            @RequestParam(name = "title") String title,
            @RequestParam(name = "page", defaultValue = "1") int page,
            @AuthenticationPrincipal  EmployeeDto writer) {
        log.info("[CalendarController] start ============================");
        log.info("[CalendarController] schedule " + title);
        log.info("[CalendarController] page" + page);

        Page<CalendarDTO> scheduleList = calendarService.selectScheduleListbyTitle(page, title, writer);

        PagingButtonInfo pageInfo = Pagenation.getPagingButtonInfo(scheduleList);

        log.info("[CalendarController] : pageInfo : {}", pageInfo);

        ResponseDtoWithPaging responseDtoWithPaging = new ResponseDtoWithPaging();
        responseDtoWithPaging.setPageInfo(pageInfo);
        responseDtoWithPaging.setData(scheduleList.getContent());

        log.info("[CalendarController] end ============================");
        return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, "검색 조회 성공", responseDtoWithPaging));
    }

    /* 3-1 . 일정 상세를 보여주기 */
    @GetMapping("/schedule/{schCode}")
    public ResponseEntity<ResponseDto> selectScheduleDetail(@PathVariable Long schCode) {
        return ResponseEntity
                .ok().body(new ResponseDto(HttpStatus.OK, "상세 조회 성공", calendarService.selectScheduleDetail(schCode)));
    }



    /* 리퀘스트 바디를 사용하여 json형식으로 보낸다. */
    /* 4. 일정 수정하기 */
    @PutMapping("/schedule/{schCode}")
    public ResponseEntity<ResponseDto> modifyCalendar(
            @RequestBody CalendarDTO calendarDTO) {

        /* schCode로 값을 받아서 수정 */
        calendarService.modifyCalendar(calendarDTO);

        return ResponseEntity.ok()
                .body(new ResponseDto(HttpStatus.OK, "수정 완료"));
    }

    /* 5. 일정 삭제하기 */
    @DeleteMapping("/schedule/delete/{schCode}")
    public ResponseEntity<ResponseDto> deleteSchedule(@PathVariable Long schCode) {
        calendarService.deleteSchedule(schCode);
        return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, "삭제 완료"));
    }

}
