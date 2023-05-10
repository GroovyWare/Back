package com.groovy.ware.member.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import com.groovy.ware.member.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long>{

	/* 1. 회원 전체 조회 리스트 */
	@EntityGraph(attributePaths= {"History","Pass"})
	Page<Member> findAll(Pageable pageable);
	
}
