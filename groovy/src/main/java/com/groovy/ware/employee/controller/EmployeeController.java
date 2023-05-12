// package com.groovy.ware.employee.controller;

// import org.springframework.data.domain.Page;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestParam;
// import org.springframework.web.bind.annotation.RestController;

// import com.groovy.ware.common.ResponseDto;
// import com.groovy.ware.common.paging.Pagenation;
// import com.groovy.ware.common.paging.PagingButtonInfo;
// import com.groovy.ware.common.paging.ResponseDtoWithPaging;
// import com.groovy.ware.employee.dto.EmployeeDto;
// import com.groovy.ware.employee.service.EmployeeService;

// import lombok.extern.slf4j.Slf4j;

// @RestController
// @RequestMapping("/auth/emp")
// @Slf4j
// public class EmployeeController {
	
// 	private EmployeeService employeeService;
	
// 	public EmployeeController(EmployeeService employeeService) {
// 		this.employeeService = employeeService;
// 	}
	
// 	/* 직원 목록 조회 (전체) */
	
// 	public ResponseEntity<ResponseDto> selectEmployeeList(@RequestParam(name="page", defaultValue="1") int page) {
		
// 		log.info("[EmployeeController] : selectEmployeeList start ==================================");
// 		log.info("[EmployeeController] : page : {}", page);
		
// 		Page<EmployeeDto> employeeDtoList = employeeService.selectEmployeeList(page);
		
// 		PagingButtonInfo pageInfo = Pagenation.getPagingButtonInfo(employeeDtoList);
// 		log.info("[EmployeeController] : pageInfo : {}", pageInfo);
		
// 		ResponseDtoWithPaging responseDtoWithPaging = new ResponseDtoWithPaging();
// 		responseDtoWithPaging.setPageInfo(pageInfo);
// 		responseDtoWithPaging.setData(employeeDtoList.getContent());
		
// 		log.info("[EmployeeController] : selectEmployeeList end ==================================");
		
// 		return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, "직원 목록 조회 되었습니다.",responseDtoWithPaging));
		
// 	}
	

// }
