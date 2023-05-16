package com.groovy.ware.employee.repository;


import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import com.groovy.ware.employee.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {


	/* 직원 목록 조회 (전체)*/
	@EntityGraph(attributePaths={"dept", "position", "file"})
	Page<Employee> findAll(Pageable pageable);
	
	/* 아이디 중복 확인시 조회 */
	Optional<Employee> findByEmpId(String empId);
	
	Employee findByEmpCode(String empCode);

}
