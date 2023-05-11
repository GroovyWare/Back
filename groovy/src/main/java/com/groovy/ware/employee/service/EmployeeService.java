package com.groovy.ware.employee.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.groovy.ware.employee.EmployeeRepository;
import com.groovy.ware.employee.dto.EmployeeDto;
import com.groovy.ware.employee.entity.Employee;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class EmployeeService {

	private final EmployeeRepository employeeRepository;
	private final ModelMapper modelMapper;
	
	public EmployeeService(EmployeeRepository employeeRepository, ModelMapper modelMapper) {
		this.employeeRepository = employeeRepository;
		this.modelMapper = modelMapper;
	}
	
	@Value("${image.image-url")
	private String IMAGE_URL;
	
	/* 직원 목록 조회 */
	public Page<EmployeeDto> selectEmployeeList(int page) {
		
		log.info("[EmployeeService] selectEmployeeList start ============================================");
		
		Pageable pageable = PageRequest.of(page -1, 10, Sort.by("empCode").descending());
		
		Page<Employee> employeeList = employeeRepository.findAll(pageable);
		Page<EmployeeDto> employeeDtoList = employeeList.map(employee -> modelMapper.map(employee, EmployeeDto.class));
		
		//employeeDtoList.forEach(employee -> employee.file.setImgUrl(IMAGE_URL + employee.file.setImgUrl));
		
		log.info("[EmployeeService] selectEmployeeList end ============================================");
		
		return employeeDtoList;
		
	}
}
