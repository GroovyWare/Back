package com.groovy.ware.member.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.groovy.ware.member.dto.MemberDto;
import com.groovy.ware.member.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long>{

	/* 전체 회원 리스트 */
	@EntityGraph(attributePaths={"history", "history.pass", "history.employee"})
	Page<Member> findAll(Pageable pageable);


	/* 민경 운동량 회원 목록 조회 */
	@Query(
			"SELECT p "
			+ "FROM Member p "
			+ "JOIN p.history h "
			+ "JOIN h.pass s "
			+ " WHERE p.memCode = :memCode "
			+ " AND h.resEnd < SYSDATE"
			)
	Page<Member> findByMemCode(Pageable pageable, @Param("memCode")Long memCode);


	
	/* 회원 상세 조회 */
	
	
	/* 회원 등록 */
	

	/* 회원 수정 */


}
