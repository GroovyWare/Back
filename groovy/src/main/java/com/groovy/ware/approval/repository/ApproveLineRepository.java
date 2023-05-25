package com.groovy.ware.approval.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.groovy.ware.approval.entity.Approval;
import com.groovy.ware.approval.entity.ApproveLine;
import com.groovy.ware.approval.entity.ApproveLineId;

public interface ApproveLineRepository extends JpaRepository<ApproveLine, ApproveLineId>{

	/* 결재권자 목록 찾기 */
	List<ApproveLine> findByApvCode(Integer apvCode);

	/* 결재권자 목록에서 찾기 */
	List<ApproveLine> findByEmpCode(Integer empCode);

}
