package com.groovy.ware.document.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.groovy.ware.document.Entity.Document;

public interface DocumentRepository extends JpaRepository<Document, Long> {

}
