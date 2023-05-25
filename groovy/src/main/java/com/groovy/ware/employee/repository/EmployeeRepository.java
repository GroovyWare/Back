package com.groovy.ware.employee.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.groovy.ware.employee.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {


	/* 직원 목록 조회 (전체)*/
	@EntityGraph(attributePaths={"dept", "position", "file"})
	Page<Employee> findAll(Pageable pageable);
	
	/* 직원 등록시 아이디 중복 확인을 위한 조회*/
    @Query("SELECT e FROM Employee e WHERE e.empId = :empId")
    Employee idCheck(String empId);
	
	/* 로그인시 아이디 조회 */
	@EntityGraph(attributePaths={"dept", "position", "file", "auths", "auths.auth"})
	Optional<Employee> findByEmpId(String empId);
	
	/* 아이디 중복 검사 */
	@Query("SELECT e.empId FROM Employee e")
	List<Employee> findEmpIdList();
	
	/* 민경 조직도 조회 (검색) */
	List<Employee> findByEmpName(String empName);
	
	/* 기욱 */
	Employee findByEmpCode(String keyword);

	
}
