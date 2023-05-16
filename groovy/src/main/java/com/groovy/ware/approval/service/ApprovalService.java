package com.groovy.ware.approval.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.groovy.ware.approval.repository.ApprovalRepository;
import com.groovy.ware.employee.dto.EmployeeDto;
import com.groovy.ware.employee.entity.Employee;
import com.groovy.ware.employee.repository.EmployeeRepository;
import com.groovy.ware.member.dto.MemberDto;

@Service
public class ApprovalService {
	
	private final EmployeeRepository employeeRepository;
	private final ApprovalRepository approvalRepository;
	private final ModelMapper modelMapper;
	
	public ApprovalService(EmployeeRepository employeeRepository, ApprovalRepository approvalRepository, ModelMapper modelMapper) {
		this.employeeRepository = employeeRepository;
		this.approvalRepository = approvalRepository;
		this.modelMapper = modelMapper;
	}


	public List<EmployeeDto> searchMember(String empName) {
		
		List<Employee> searchMemberList = employeeRepository.findByempName(empName);
		List<EmployeeDto> searchMemberDtoList = searchMemberList.stream().map(row -> modelMapper.map(row, EmployeeDto.class)).collect(Collectors.toList());
		
		return searchMemberDtoList;
	}

}
