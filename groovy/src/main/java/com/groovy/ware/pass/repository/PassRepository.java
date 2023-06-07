package com.groovy.ware.pass.repository;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.groovy.ware.pass.entity.Pass;

public interface PassRepository extends JpaRepository<Pass, Long>{
	
	/* 회원권 등록 */
	
	/* 회원권 조회 */
	Page<Pass> findAll(Pageable pageable);

	
	
}
