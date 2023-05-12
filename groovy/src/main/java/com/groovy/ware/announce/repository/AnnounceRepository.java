package com.groovy.ware.announce.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.groovy.ware.announce.entity.Announce;

public interface AnnounceRepository extends JpaRepository<Announce, Long> {

    Page<Announce> findByAnnTitleContaining(String keyword, Pageable pageable);

    Page<Announce> findByEmpCode(String keyword, Pageable pageable);

    Page<Announce> findByAnnContentContaining(String keyword, Pageable pageable);

    Page<Announce> findByAnnTitleContainingOrAnnContentContaining(String titleKeyword, String contentKeyword, Pageable pageable);

}