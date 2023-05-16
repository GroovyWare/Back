package com.groovy.ware.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.groovy.ware.common.entity.File;

public interface FileRepository extends JpaRepository<File, Long> {

}
