package com.groovy.ware.approval.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.groovy.ware.approval.service.ApprovalService;
import com.groovy.ware.common.dto.ResponseDto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/approval")
public class ApprovalController {
	
	private final ApprovalService approvalService;
	
	public ApprovalController(ApprovalService approvalService) {
		this.approvalService = approvalService;
	}
	
	/* 조직도 직원 목록 조회 */
	@GetMapping("/search")
	public ResponseEntity<ResponseDto> searchMember(@RequestParam String empName){
		log.info("controller start========");
		
		return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, "조회 성공", approvalService.searchMember(empName)));
	}
	
	/* 부서 조회 */
	@GetMapping("/search/dept")
	public ResponseEntity<ResponseDto> searchDept(){
		return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, "조회 성공", approvalService.searchDept()));
	}
	

}
