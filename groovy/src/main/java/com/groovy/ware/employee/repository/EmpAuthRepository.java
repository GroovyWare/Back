package com.groovy.ware.employee.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.groovy.ware.employee.entity.EmpAuth;


public interface EmpAuthRepository extends JpaRepository<EmpAuth, Long> {

	void deleteByEmpCode(Long empCode);

	
}
