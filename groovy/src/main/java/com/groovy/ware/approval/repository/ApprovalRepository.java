package com.groovy.ware.approval.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.groovy.ware.approval.entity.Approval;

public interface ApprovalRepository extends JpaRepository<Approval, Long>{

}
