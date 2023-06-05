package com.groovy.ware.equipment.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import com.groovy.ware.equipment.entity.Equipment;

public interface EquipmentRepository extends JpaRepository<Equipment, Long> {
	
	@EntityGraph(attributePaths = {"employee"})
	Page<Equipment> findAllByOrderByEqpDateDesc(PageRequest sortedPageable);
	
	Optional<Equipment> findByEmployee_EmpCode(String empCode);
	
	Page<Equipment> findByEqpTitleLike(String keyword, Pageable pageable);
	Page<Equipment> findByEqpStatusLike(String keyword, Pageable pageable);

}
