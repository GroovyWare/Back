package com.groovy.ware.member.controller;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.groovy.ware.common.ResponseDto;
import com.groovy.ware.common.paging.Pagenation;
import com.groovy.ware.common.paging.PagingButtonInfo;
import com.groovy.ware.common.paging.ResponseDtoWithPaging;
import com.groovy.ware.member.dto.MemberDto;
import com.groovy.ware.member.service.MemberService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/member")
public class MemberController {
	
	private final MemberService memberSerivce;
	
	public MemberController(MemberService memberSerivce) {
		this.memberSerivce = memberSerivce;
	}
	
	/* 1. 회원 전체 조회 리스트 */
	@GetMapping("/list")
	public ResponseEntity<ResponseDto> findMemberAll(@RequestParam(name="page", defaultValue="1") int page){
		
		log.info("[ProductController] : findMemberAll start ==================================== ");
		log.info("[ProductController] : page : {}", page);
		
		Page<MemberDto> memberDtoList = memberSerivce.findMemberAll(page);

		PagingButtonInfo pageInfo = Pagenation.getPagingButtonInfo(memberDtoList);
		
		log.info("[ProductController] : pageInfo : {}", pageInfo);
		
		
		ResponseDtoWithPaging responseDtoWithPaging = new ResponseDtoWithPaging();
		responseDtoWithPaging.setPageInfo(pageInfo);
		responseDtoWithPaging.setData(memberDtoList.getContent());
		
		log.info("[ProductController] : findMemberAll end ==================================== ");
		
		return ResponseEntity.ok()
				.body(new ResponseDto(HttpStatus.OK, "회원조회가 완료되었습니다.", responseDtoWithPaging));
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
