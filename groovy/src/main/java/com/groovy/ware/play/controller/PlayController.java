package com.groovy.ware.play.controller;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.groovy.ware.common.ResponseDto;
import com.groovy.ware.common.paging.Pagenation;
import com.groovy.ware.common.paging.PagingButtonInfo;
import com.groovy.ware.common.paging.ResponseDtoWithPaging;
import com.groovy.ware.employee.dto.EmployeeDto;
import com.groovy.ware.member.dto.MemberDto;
import com.groovy.ware.play.service.PlayService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/play")
public class PlayController {
	
	private final PlayService playService;
	
	public PlayController(PlayService playService) {
		this.playService = playService;
	}
	
	/* 1.각 트레이너별 회원 목록 조회 */
	@GetMapping("/memberList")
	public ResponseEntity<ResponseDto> selectMemberList(@RequestParam(name="page", defaultValue="1") int page, @AuthenticationPrincipal EmployeeDto employeeDto){
		
		Page<MemberDto> memberList = playService.selectMemberList(page, employeeDto.getEmpId());
		
		PagingButtonInfo pageInfo = Pagenation.getPagingButtonInfo(memberList);
		
		ResponseDtoWithPaging responseDtoWithPaging = new ResponseDtoWithPaging();
		responseDtoWithPaging.setPageInfo(pageInfo);
		responseDtoWithPaging.setData(memberList.getContent());
		
		return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, "조회 성공", responseDtoWithPaging));
	}

}
