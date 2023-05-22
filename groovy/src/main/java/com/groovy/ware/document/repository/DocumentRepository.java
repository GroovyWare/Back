package com.groovy.ware.document.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.groovy.ware.document.Entity.Document;

public interface DocumentRepository extends JpaRepository<Document, Long> {

	/* 이름에 따른 기안서 양식 찾기 */
	Document findByDocTitle(String docTitle);

}
