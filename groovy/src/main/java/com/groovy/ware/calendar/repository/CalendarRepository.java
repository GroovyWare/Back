package com.groovy.ware.calender.repository;



import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CalendarRepository extends JpaRepository {
    
    @EntityGraph
    

}
