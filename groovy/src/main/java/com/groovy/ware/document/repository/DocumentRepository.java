package com.groovy.ware.document.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.groovy.ware.document.Entity.Document;

public interface DocumentRepository extends JpaRepository<Document, Long> {

	Document findByDocCode(long l);

}
