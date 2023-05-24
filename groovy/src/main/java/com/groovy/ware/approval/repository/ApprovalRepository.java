package com.groovy.ware.approval.repository;

import java.util.Optional;

import javax.persistence.LockModeType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.groovy.ware.approval.entity.Approval;

public interface ApprovalRepository extends JpaRepository<Approval, Integer>{

}
