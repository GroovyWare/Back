package com.groovy.ware.member.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import com.groovy.ware.employee.entity.Employee;
import com.groovy.ware.member.entity.Member;
import com.groovy.ware.play.entity.Play;

public interface MemberRepository extends JpaRepository<Member, Long>{

	/* 전체 회원 리스트 */
	@EntityGraph(attributePaths={"history", "employee"})
	Page<Member> findAll(Pageable pageable);

	/* 민경 멤버 리스트 */
	Page<Member> findByEmpCode(Pageable pageable, Long memCode);


	
	/* 회원 상세 조회 */
	
	
	/* 회원 등록 */
	

	/* 회원 수정 */


}
