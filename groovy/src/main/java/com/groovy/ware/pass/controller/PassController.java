package com.groovy.ware.pass.controller;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.groovy.ware.common.ResponseDto;
import com.groovy.ware.common.paging.Pagenation;
import com.groovy.ware.common.paging.PagingButtonInfo;
import com.groovy.ware.common.paging.ResponseDtoWithPaging;
import com.groovy.ware.pass.dto.PassDto;
import com.groovy.ware.pass.service.PassService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/pass")
public class PassController {

	private final PassService passService;
	
	public PassController(PassService passService) {
		this.passService = passService;
		
	}
	
	
	/* 회원권 등록 */
	@PostMapping("/regist")
	public ResponseEntity<ResponseDto> insertPass(@ModelAttribute PassDto passDto){
		
		passService.insertPass(passDto);
		
		return ResponseEntity.ok()
				.body(new ResponseDto(HttpStatus.OK, " 회원권 등록 성공"));
		
	}
	
	
	/* 회원권 조회 */
	@GetMapping("list")
	public ResponseEntity<ResponseDto> findPassList(@RequestParam(name="page", defaultValue="1") int page){
		
		log.info("[PassController] : findPassList start ==================================== ");
		log.info("[PassController] : page : {}", page);
		
		Page<PassDto> passDtoList = passService.findPassList(page);
		
		PagingButtonInfo pageInfo = Pagenation.getPagingButtonInfo(passDtoList);
		
		log.info("[PassController] : pageInfo : {}", pageInfo);
		
		ResponseDtoWithPaging responseDtoWithPaging = new ResponseDtoWithPaging();
		responseDtoWithPaging.setPageInfo(pageInfo);
		responseDtoWithPaging.setData(passDtoList.getContent());
		
		log.info("[PassController] : findPassList end ==================================== ");
		
		
		return ResponseEntity.ok()
				.body(new ResponseDto(HttpStatus.OK, "리스트 조회 성공", responseDtoWithPaging));
	}
	
	
	
	
	
	
	
	
	
	
	
}