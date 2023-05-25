package com.groovy.ware.equipment.repository;

import com.groovy.ware.equipment.entity.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EquipmentRepository extends JpaRepository<Equipment, Long> {
	
	
	
}