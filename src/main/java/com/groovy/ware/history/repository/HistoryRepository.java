package com.groovy.ware.history.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.groovy.ware.history.entity.History;

public interface HistoryRepository extends JpaRepository<History, String> {

}
