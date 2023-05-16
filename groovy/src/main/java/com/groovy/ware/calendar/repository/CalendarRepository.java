package com.groovy.ware.calendar.repository;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.groovy.ware.calendar.entity.Calendar;
import com.groovy.ware.employee.entity.Employee;

public interface CalendarRepository extends JpaRepository<Calendar, Long> {
   /* 제목으로 찾기 */
   @Query("SELECT s FROM Calendar s WHERE s.schTitle LIKE %:schTitle%")
   Page<Calendar> findBySchTitle(Pageable pageable, String schTitle);

   /* 일정코드로 찾기? */
   @Query("SELECT s FROM Calendar s WHERE s.schCode = :schCode")
   Calendar findBySchCode(@Param("schCode") Long schCode);

   /* 캘린더 메인 */
   // @EntityGraph(attributePaths = {"schWriter"})
   // @Query("SELECT s FROM Calendar s WHERE s.schWriter = :employee")
   // List<Calendar> findByAllscheduleswithEmpCode(@Param("empCode") Long employee);
   
   @Query("SELECT s FROM Calendar s WHERE s.schDiv ='전체' OR (s.schDiv = '부서' AND s.dept.deptCode = :deptCode) OR s.schWriter.empCode = :empCode")
   List<Calendar> findByAllSchedulesWithEmpCode(@Param("empCode") Long empCode, @Param("deptCode") Long deptCode);
   
}
