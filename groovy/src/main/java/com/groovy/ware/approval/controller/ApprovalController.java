package com.groovy.ware.approval.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
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
import com.groovy.ware.common.paging.Pagenation;
import com.groovy.ware.common.paging.PagingButtonInfo;
import com.groovy.ware.common.paging.ResponseDtoWithPaging;
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
	public ResponseEntity<ResponseDto> searchMember(@RequestParam String empName) {
		return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, "조회 성공", approvalService.searchMember(empName)));
	}

	/* 부서 조회 */
	@GetMapping("/search/dept")
	public ResponseEntity<ResponseDto> searchDept() {
		return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, "조회 성공", approvalService.searchDept()));
	}

	/* 기안서 작성자 찾기 */
	@GetMapping("/search/employee")
	public ResponseEntity<ResponseDto> searchEmployee(@AuthenticationPrincipal EmployeeDto employeeDto) {
		return ResponseEntity.ok()
				.body(new ResponseDto(HttpStatus.OK, "조회 성공", approvalService.searchEmployee(employeeDto)));
	}

	/* 기안서 작성 */
	@PostMapping("/save")
	public ResponseEntity<ResponseDto> saveVacationHtml(@ModelAttribute ApprovalDto approvalDto,
			@AuthenticationPrincipal EmployeeDto employeeDto, @RequestParam String docTitle) {

		DocumentDto document = approvalService.selectDocumentCode(docTitle);

		approvalDto.setEmployee(employeeDto);
		approvalDto.setDocument(document);
		approvalService.saveVacationHtml(approvalDto);

		return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, "저장 완료"));
	}

	/* 결재 요청 목록 조회 */
	@GetMapping("/request")
	public ResponseEntity<ResponseDto> searchRequest(@RequestParam(name = "page", defaultValue = "1") int page,
	        @AuthenticationPrincipal EmployeeDto employeeDto) {

	    Map<String, Object> requestList = approvalService.searchRequest(page, employeeDto);
	    Page<ApprovalDto> approvalDtoPage = (Page<ApprovalDto>) requestList.get("approvalDto");
	    List<EmployeeDto> employeeDtoList = (List<EmployeeDto>)requestList.get("employeeDto");

	    PagingButtonInfo pageInfo = Pagenation.getPagingButtonInfo(approvalDtoPage);

	    ResponseDtoWithPaging responseDtoWithPaging = new ResponseDtoWithPaging();
	    responseDtoWithPaging.setPageInfo(pageInfo);
	    
	    Map<String, Object> data = new HashMap<>();
	    data.put("approvalDtoContent", approvalDtoPage.getContent());
	    data.put("employeeDtoList", employeeDtoList);
	    responseDtoWithPaging.setData(data);

	    return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, "조회 성공", responseDtoWithPaging));
	}
	
	/* 결재 요청 목록 디테일 조회 */
	@GetMapping("/context")
	public ResponseEntity<ResponseDto> searchContext(@RequestParam Integer apvCode){
		return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, "조회 성공", approvalService.searchContext(apvCode)));
	}
	
	/* 결재 대기 목록 조회 */
	@GetMapping("/wait")
	public ResponseEntity<ResponseDto> searchWait(@RequestParam(name = "page", defaultValue = "1") int page,
	        @AuthenticationPrincipal EmployeeDto employeeDto) {

		
		Page<ApprovalDto> requestList = approvalService.searchWait(page, employeeDto);

	    PagingButtonInfo pageInfo = Pagenation.getPagingButtonInfo(requestList);

	    ResponseDtoWithPaging responseDtoWithPaging = new ResponseDtoWithPaging();
	    responseDtoWithPaging.setPageInfo(pageInfo);
	    responseDtoWithPaging.setData(requestList.getContent());

	    return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, "조회 성공", responseDtoWithPaging));
	}
	
	/* 현재 로그인한 사람 정보 찾기 */
	@GetMapping("/now")
	public ResponseEntity<ResponseDto> searchNow(@AuthenticationPrincipal EmployeeDto employeeDto){
		return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, "조회 완료", approvalService.searchNow(employeeDto)));
	}
	
//	/* 조회수 증가 */
//	@PostMapping("/count")
//	public ResponseEntity<ResponseDto> addCount(@RequestParam Integer apvCode, @RequestBody Integer count){
//		
//		approvalService.addCount(apvCode, count);
//		
//		return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, "변경 성공"));
//	}

}
