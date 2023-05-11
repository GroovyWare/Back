package com.groovy.ware.calendar.repository;



import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;

import com.groovy.ware.calendar.entity.Calendar;

public interface CalendarRepository extends JpaRepository<Calendar, Long> {
    
    List<Calendar> findBySchTitle(String schTitle);

}
