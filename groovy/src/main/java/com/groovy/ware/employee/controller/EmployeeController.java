package com.groovy.ware.employee.controller;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.groovy.ware.common.dto.ResponseDto;
import com.groovy.ware.common.paging.Pagenation;
import com.groovy.ware.common.paging.PagingButtonInfo;
import com.groovy.ware.common.paging.ResponseDtoWithPaging;
import com.groovy.ware.employee.dto.EmployeeDto;
import com.groovy.ware.employee.service.EmployeeService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/auth")
@Slf4j
public class EmployeeController {
	
	private EmployeeService employeeService;
	
	public EmployeeController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}
	
	/* 직원 목록 조회 (전체) */
	@GetMapping("/emp")
	public ResponseEntity<ResponseDto> selectEmployeeList(@RequestParam(name="page", defaultValue="1") int page) {
		
		log.info("[EmployeeController] : selectEmployeeList start ==================================");
		log.info("[EmployeeController] : page : {}", page);
		
		Page<EmployeeDto> employeeDtoList = employeeService.selectEmployeeList(page);
		
		PagingButtonInfo pageInfo = Pagenation.getPagingButtonInfo(employeeDtoList);
		log.info("[EmployeeController] : pageInfo : {}", pageInfo);
		
		ResponseDtoWithPaging responseDtoWithPaging = new ResponseDtoWithPaging();
		responseDtoWithPaging.setPageInfo(pageInfo);
		responseDtoWithPaging.setData(employeeDtoList.getContent());
		
		log.info("[EmployeeController] : selectEmployeeList end ==================================");
		
		return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, "전체 직원 목록이 조회 되었습니다.",responseDtoWithPaging));
	}
	
	/* 직원 상세 조회 */
	@GetMapping("/emp/{empCode}")
	public ResponseEntity<ResponseDto> selectEmployee(@PathVariable Long empCode){
		
		return ResponseEntity
				.ok()
				.body(new ResponseDto(HttpStatus.OK, "직원의 상세 정보 조회되었습니다.", employeeService.selectEmployee(empCode)));
	}

	/* 직원 정보 수정 */
	@PutMapping("/emp/{empCode}")
	public ResponseEntity<ResponseDto> updateEmployee(@ModelAttribute EmployeeDto employeeDto) {
		
		employeeService.updateEmployee(employeeDto);
		
		return ResponseEntity
				.ok()
				.body(new ResponseDto(HttpStatus.OK, "직원 정보 수정되었습니다."));
	}
	
	/* 직원 등록 */
	@PostMapping("/emp")
	public ResponseEntity<ResponseDto> insertEmployee(@ModelAttribute EmployeeDto employeeDto) {
		
		employeeService.insertEmployee(employeeDto);
		
		return ResponseEntity
				.ok()
				.body(new ResponseDto(HttpStatus.OK, "직원 정보가 등록되었습니다."));
	}
	
}
