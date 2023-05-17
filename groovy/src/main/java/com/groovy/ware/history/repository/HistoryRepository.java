package com.groovy.ware.history.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import com.groovy.ware.employee.entity.Employee;
import com.groovy.ware.history.entity.History;
import com.groovy.ware.member.entity.Member;

public interface HistoryRepository extends JpaRepository<History, Long> {
	

	/* 회원 개인 이력 상세 조회 */
	@EntityGraph(attributePaths={"pass"})
	Page<History> findByMemCode(Pageable pageable, Long memCode);

	/* 민경 회원 목록 조회 */
	History findByEmployeeEmpCode(Long empCode);


}
