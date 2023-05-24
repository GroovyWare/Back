package com.groovy.ware.approval.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.groovy.ware.approval.entity.Approval;
import com.groovy.ware.approval.entity.ApproveLine;
import com.groovy.ware.approval.entity.ApproveLineId;

public interface ApproveLineRepository extends JpaRepository<ApproveLine, ApproveLineId>{

}
