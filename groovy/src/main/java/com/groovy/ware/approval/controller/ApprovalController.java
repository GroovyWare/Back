package com.groovy.ware.approval.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.groovy.ware.approval.service.ApprovalService;
import com.groovy.ware.common.dto.ResponseDto;

@RestController
@RequestMapping("/approval")
public class ApprovalController {
	
	private final ApprovalService approvalService;
	
	public ApprovalController(ApprovalService approvalService) {
		this.approvalService = approvalService;
	}
	
	@GetMapping("/search")
	public ResponseEntity<ResponseDto> searchMember(@PathVariable String name){
		
		
		return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, "조회 성공", approvalService.searchMember(name)));
	}

}
