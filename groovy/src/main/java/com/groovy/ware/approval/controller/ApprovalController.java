package com.groovy.ware.approval.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.groovy.ware.approval.dto.ApprovalDto;
import com.groovy.ware.approval.service.ApprovalService;
import com.groovy.ware.common.dto.ResponseDto;
import com.groovy.ware.document.Entity.Document;
import com.groovy.ware.document.dto.DocumentDto;
import com.groovy.ware.employee.dto.EmployeeDto;

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
	
	/* 기안서 작성자 찾기 */
	@GetMapping("/search/employee")
	public ResponseEntity<ResponseDto> searchEmployee(@AuthenticationPrincipal EmployeeDto employeeDto){
		return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, "조회 성공", approvalService.searchEmployee(employeeDto)));
	}
	
	/* 기안서 작성 */
	@PostMapping("/save")
	public ResponseEntity<ResponseDto> saveVacationHtml(@ModelAttribute ApprovalDto approvalDto, @AuthenticationPrincipal EmployeeDto employeeDto, @RequestParam String docTitle){
		
		log.info("controller start ===================== ");
		log.info("approvalDto {}", approvalDto);
		
		DocumentDto document = approvalService.selectDocumentCode(docTitle);
		
		approvalDto.setEmployee(employeeDto);
		approvalDto.setDocument(document);	
 		approvalService.saveVacationHtml(approvalDto);
		
		log.info("approvalDto {}", approvalDto);
		
		return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, "저장 완료"));
	}

}
