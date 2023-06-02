package com.groovy.ware.approval.repository;

import java.util.List;
import java.util.Optional;

import javax.persistence.LockModeType;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.groovy.ware.approval.dto.ApproveLineDto;
import com.groovy.ware.approval.entity.Approval;
import com.groovy.ware.approval.entity.ApproveLine;
import com.groovy.ware.employee.dto.EmployeeDto;
import com.groovy.ware.employee.entity.Employee;

public interface ApprovalRepository extends JpaRepository<Approval, Integer>{

	/* 결재 요청 목록 찾기 */ 
	Page<Approval> findByEmployee(Pageable pageable, Employee employee);

	/* 결재권자 목록 찾기 */
	List<Approval> findByEmployee(Employee employee);

	/* 결재 요청 목록 디테일 조회 */
	Approval findByApvCode(Integer apvCode);

	/* 결재 대기 목록 조회 */
	Page<Approval> findByApproveLineIn(Pageable pageable, List<ApproveLine> approveLines);

	/* 결재 목록에 맞는 결재권자 찾기 */
	List<Approval> findAllByApvCode(Integer apvCode);

}
