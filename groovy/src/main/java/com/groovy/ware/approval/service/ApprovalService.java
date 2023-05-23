package com.groovy.ware.approval.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.groovy.ware.approval.dto.ApprovalDto;
import com.groovy.ware.approval.dto.ApproveLineDto;
import com.groovy.ware.approval.entity.Approval;
import com.groovy.ware.approval.entity.ApproveLine;
import com.groovy.ware.approval.repository.ApprovalRepository;
import com.groovy.ware.document.dto.DocumentDto;
import com.groovy.ware.document.repository.DocumentRepository;
import com.groovy.ware.employee.dto.DepartmentDto;
import com.groovy.ware.employee.dto.EmployeeDto;
import com.groovy.ware.employee.entity.Department;
import com.groovy.ware.employee.entity.Employee;
import com.groovy.ware.employee.repository.DepartmentRepository;
import com.groovy.ware.employee.repository.EmployeeRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ApprovalService {
	
	private final EmployeeRepository employeeRepository;
	private final ApprovalRepository approvalRepository;
	private final DepartmentRepository departmentRepository;
	private final DocumentRepository documentRepository;
	private final ModelMapper modelMapper;
	
	public ApprovalService(EmployeeRepository employeeRepository, ApprovalRepository approvalRepository, ModelMapper modelMapper, DepartmentRepository departmentRepository, DocumentRepository documentRepository) {
		this.employeeRepository = employeeRepository;
		this.approvalRepository = approvalRepository;
		this.departmentRepository = departmentRepository;
		this.documentRepository = documentRepository;
		this.modelMapper = modelMapper;
	}


	/* 조직도 회원 목록 조회 */
	public List<EmployeeDto> searchMember(String empName) {
		
		log.info("service start=========");
		log.info(empName);
		List<Employee> searchEmpList = employeeRepository.findByEmpName(empName);
		log.info("searchMemberList {}", searchEmpList.toString());
		List<EmployeeDto> searchEmpDtoList = searchEmpList.stream().map(row -> modelMapper.map(row, EmployeeDto.class)).collect(Collectors.toList());
		log.info(searchEmpDtoList.toString());
		
		log.info("service end===========");
		
		return searchEmpDtoList;
	}

	/* 조직도 부서 조회 */
	public List<DepartmentDto> searchDept() {
		
		List<Department> searchDept = departmentRepository.findAll();
		List<DepartmentDto> searchDeptDto = searchDept.stream().map(row -> modelMapper.map(row, DepartmentDto.class)).collect(Collectors.toList());
		
		return searchDeptDto;
	}


	/* 결재 */
	@Transactional
	public void saveVacationHtml(ApprovalDto approvalDto) {
		
		approvalRepository.save(modelMapper.map(approvalDto, Approval.class));

	}

	/* 문서 코드 찾기 */
	public DocumentDto selectDocumentCode(String docTitle) {
		return modelMapper.map(documentRepository.findByDocTitle(docTitle), DocumentDto.class);
	}

	/* 기안자 찾기 */
	public List<EmployeeDto> searchEmployee(EmployeeDto employeeDto) {
		
		List<Employee> findEmployee = employeeRepository.findByEmpName(employeeDto.getEmpName());
		List<EmployeeDto> findEmployeeDto = findEmployee.stream().map(row -> modelMapper.map(row, EmployeeDto.class)).collect(Collectors.toList());
		
		return findEmployeeDto;
	}

}
