package com.groovy.ware.employee.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.groovy.ware.employee.entity.Department;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

}
