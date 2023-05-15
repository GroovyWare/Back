package com.groovy.ware.employee.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.groovy.ware.employee.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	/* 직원 목록 조회 (근속중) */
	Page<Employee> findByEmpStatus(Pageable pageable, String empStauts);
	
	/* 직원 목록 조회 (전체)*/
	// findAll 메소드 pagenation 포함
	
	/* 직원 상세 조회 */
	// empCode로 조회
	
	/* 직원 등록 - save*/
	
	/* 직원 수정 - findByEmpCode로 조회 후 setter메소드로 저장하면 변화 감지해서 update  */
}
