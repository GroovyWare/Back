package com.groovy.ware.play.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.groovy.ware.member.entity.Member;
import com.groovy.ware.play.entity.Play;

public interface PlayRepository extends JpaRepository<Play, Long>{

	

}
