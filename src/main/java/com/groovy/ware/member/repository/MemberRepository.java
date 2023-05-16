package com.groovy.ware.member.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import com.groovy.ware.employee.entity.Employee;
import com.groovy.ware.member.entity.Member;
import com.groovy.ware.play.entity.Play;

public interface MemberRepository extends JpaRepository<Member, Long>{

	/* 1. 회원 전체 조회 리스트 */
	Page<Member> findAll(Pageable pageable);

	/* 2. 회원 조회 리스트(민경) */ 
	Page<Member> findByEmpCode(Pageable pageable, Employee employee);
	
}
