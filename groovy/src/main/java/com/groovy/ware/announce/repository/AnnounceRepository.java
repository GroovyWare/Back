package com.groovy.ware.announce.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import com.groovy.ware.announce.entity.Announce;
import com.groovy.ware.employee.entity.Employee;

public interface AnnounceRepository extends JpaRepository<Announce, Long> {

    @EntityGraph(attributePaths = {"empCode"})
    Page<Announce> findByAnnTitleContaining(String keyword, Pageable pageable);

    @EntityGraph(attributePaths = {"empCode"})
    Page<Announce> findByEmployee(Employee employee, Pageable pageable);

    @EntityGraph(attributePaths = {"empCode"})
    Page<Announce> findByAnnContentContaining(String keyword, Pageable pageable);

    @EntityGraph(attributePaths = {"empCode"})
    Page<Announce> findByAnnTitleContainingOrAnnContentContaining(String titleKeyword, String contentKeyword, Pageable pageable);
}