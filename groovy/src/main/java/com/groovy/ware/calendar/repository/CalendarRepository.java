package com.groovy.ware.calendar.repository;



import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.groovy.ware.calendar.entity.Calendar;

public interface CalendarRepository extends JpaRepository<Calendar, Long> {
   
   Page<Calendar> findBySchTitle(Pageable pageable, String schTitle);

   
}
