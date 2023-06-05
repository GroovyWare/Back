package com.groovy.ware.approval.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

import com.groovy.ware.approval.dto.ApprovalDto;
import com.groovy.ware.approval.dto.ApproveLineDto;
import com.groovy.ware.approval.entity.Approval;
import com.groovy.ware.approval.entity.ApproveLine;
import com.groovy.ware.approval.repository.ApprovalRepository;
import com.groovy.ware.approval.repository.ApproveLineRepository;
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
	private final ApproveLineRepository approveLineRepository;
	private final ModelMapper modelMapper;

	public ApprovalService(EmployeeRepository employeeRepository, ApprovalRepository approvalRepository,
			ModelMapper modelMapper, DepartmentRepository departmentRepository, DocumentRepository documentRepository,
			ApproveLineRepository approveLineRepository) {
		this.employeeRepository = employeeRepository;
		this.approvalRepository = approvalRepository;
		this.departmentRepository = departmentRepository;
		this.documentRepository = documentRepository;
		this.approveLineRepository = approveLineRepository;
		this.modelMapper = modelMapper;
	}

	/* 조직도 회원 목록 조회 */
	public List<EmployeeDto> searchMember(String empName) {

		log.info("service start=========");
		log.info(empName);
		List<Employee> searchEmpList = employeeRepository.findByEmpName(empName);
		log.info("searchMemberList {}", searchEmpList.toString());
		List<EmployeeDto> searchEmpDtoList = searchEmpList.stream().map(row -> modelMapper.map(row, EmployeeDto.class))
				.collect(Collectors.toList());
		log.info(searchEmpDtoList.toString());

		log.info("service end===========");

		return searchEmpDtoList;
	}

	/* 조직도 부서 조회 */
	public List<DepartmentDto> searchDept() {

		List<Department> searchDept = departmentRepository.findAll();
		List<DepartmentDto> searchDeptDto = searchDept.stream().map(row -> modelMapper.map(row, DepartmentDto.class))
				.collect(Collectors.toList());

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
		List<EmployeeDto> findEmployeeDto = findEmployee.stream().map(row -> modelMapper.map(row, EmployeeDto.class))
				.collect(Collectors.toList());

		return findEmployeeDto;
	}

	/* 결재 요청 목록 */
	public Map<String, Object> searchRequest(int page, EmployeeDto employeeDto) {

		Pageable pageable = PageRequest.of(page - 1, 10, Sort.by("apvCode").ascending());

		Employee employee = employeeRepository.findById(employeeDto.getEmpCode())
				.orElseThrow(() -> new IllegalArgumentException("일치하는 회원이 없습니다."));

		Page<Approval> searchRequest = approvalRepository.findByEmployee(pageable, employee);
		Page<ApprovalDto> searchRequestDto = searchRequest.map(row -> modelMapper.map(row, ApprovalDto.class));

		List<List<ApproveLine>> approveLineList = searchRequest.map(row -> row.getApproveLine()).getContent();

		List<Long> empCodes = new ArrayList<>();
		for (List<ApproveLine> approveLineSubList : approveLineList) {
			for (ApproveLine approveLine : approveLineSubList) {
				empCodes.add(Long.parseLong(approveLine.getEmpCode().toString()));
			}
		}

		List<Employee> employeeList = employeeRepository.findByEmpCodeIn(empCodes);
		List<EmployeeDto> employeeDtoList = employeeList.stream().map(row -> modelMapper.map(row, EmployeeDto.class))
				.collect(Collectors.toList());

		Map<String, Object> result = new HashMap<>();

		result.put("approvalDto", searchRequestDto);
		result.put("employeeDto", employeeDtoList);

		return result;
	}

	/* 기안 문서 찾기 */
	public ApprovalDto searchContext(Integer apvCode) {

		Approval approval = approvalRepository.findByApvCode(apvCode);
		ApprovalDto approvalDto = modelMapper.map(approval, ApprovalDto.class);

		return approvalDto;
	}

	/* 결재 대기 목록 조회 */
	public Page<ApprovalDto> searchWait(int page, EmployeeDto employeeDto) {
		
		log.info("hello");
		
		Pageable pageable = PageRequest.of(page - 1, 10, Sort.by("apvCode").ascending());
		Employee employee = employeeRepository.findByEmpId(employeeDto.getEmpId())
				.orElseThrow(() -> new IllegalArgumentException("해당 사원이 없습니다."));

		List<ApproveLine> approveLines = approveLineRepository
				.findByEmpCode(Integer.parseInt(employee.getEmpCode().toString()));

		Page<Approval> searchRequest = approvalRepository.findByApproveLineIn(pageable, approveLines);
		Page<ApprovalDto> searchRequestDto = searchRequest.map(row -> modelMapper.map(row, ApprovalDto.class));
		
		log.info("hi");

		return searchRequestDto;
	}

	/* 현재 로그인 한 사람의 정보 찾기 */
	public EmployeeDto searchNow(EmployeeDto employeeDto) {

		Employee employee = employeeRepository.findByEmpId(employeeDto.getEmpId())
				.orElseThrow(() -> new IllegalArgumentException("일치하는 직원이 없습니다."));
		EmployeeDto employeeDto2 = modelMapper.map(employee, EmployeeDto.class);

		return employeeDto2;
	}

	/* 결재권자 이름 찾기 */
	public List<EmployeeDto> searchApproveLine(List<Long> empCode) {

		List<Employee> employeeList = employeeRepository.findAllById(empCode);
		List<EmployeeDto> employeeDtoList = employeeList.stream().map(row -> modelMapper.map(row, EmployeeDto.class))
				.collect(Collectors.toList());

		return employeeDtoList;
	}

	/* 승인 반려 상태 업데이트 */
	public void updateStatus(EmployeeDto employeeDto, ApprovalDto approvalDto) {

		Integer empCode = Integer.parseInt(employeeDto.getEmpCode().toString());

		Approval approval = approvalRepository.findByApvCode(approvalDto.getApvCode());

		List<ApproveLine> approveLines = approval.getApproveLine();

		approveLines.stream().filter(approveLine -> approveLine.getEmpCode().equals(empCode))
				.forEach(matchingApproveLine -> {
					matchingApproveLine.setAplStatus(approvalDto.getApproveLine().get(0).getAplStatus());
					matchingApproveLine.setAplDate(approvalDto.getApproveLine().get(0).getAplDate());
				});
		
		if(approveLines.stream().allMatch(approveLine -> approveLine.getAplStatus().equals("승인"))) {
			approval.setApvStatus("승인");
			approval.setApvEndDate(new Date());
		}else if(approveLines.stream().anyMatch(approveLine -> approveLine.getAplStatus().equals("반려"))){
			approval.setApvStatus("반려");
			approval.setApvEndDate(new Date());
		}
		
		approvalRepository.save(approval);
	}
	
	/* 결재 목록 조회 */
	public Page<ApprovalDto> searchList(int page, EmployeeDto employeeDto) {
		
		Pageable pageable = PageRequest.of(page - 1, 10, Sort.by("apvCode").ascending());
		
		Employee employee = employeeRepository.findById(employeeDto.getEmpCode()).orElseThrow(() -> new IllegalArgumentException("일치하는 회원이 없습니다."));
		Page<Approval> approvalList = approvalRepository.findByEmployee(pageable, employee);
		Page<ApprovalDto> approvalDto = approvalList.map(row -> modelMapper.map(row, ApprovalDto.class));
		
		return approvalDto;
	}
}
