package com.groovy.ware.calendar.repository;


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
   @Query("SELECT s FROM Calendar s "+
   "WHERE s.title LIKE %:title% "+
   "AND (s.schDiv ='전체' OR (s.schDiv = '부서' AND s.dept.deptCode = :deptCode) OR s.schWriter.empCode = :empCode)" )
   Page<Calendar> findByTitle(Pageable pageable, @Param("title") String title, @Param("empCode") Long empCode, @Param("deptCode") Long deptCode);

   /* 일정코드로 찾기? */
   @Query("SELECT s FROM Calendar s WHERE s.schCode = :schCode")
   Calendar findBySchCode(@Param("schCode") Long schCode);

   /* 캘린더 메인 */
   // @EntityGraph(attributePaths = {"schWriter"})
   // @Query("SELECT s FROM Calendar s WHERE s.schWriter = :employee")
   // List<Calendar> findByAllscheduleswithEmpCode(@Param("empCode") Long employee);
   
   /* jpql 사용 -> 쿼리메소드를 쓰기에는 너무 길어진다. + 복잡해짐 / 네이티브 쿼리는 jpql을 쓸수 있을 때는 사용하지 않는 편이 권장된다. */
   @Query("SELECT s FROM Calendar s WHERE s.schDiv ='전체' OR (s.schDiv = '부서' AND s.dept.deptCode = :deptCode) OR s.schWriter.empCode = :empCode")
   List<Calendar> findByAllSchedulesWithEmpCode(@Param("empCode") Long empCode, @Param("deptCode") Long deptCode);
   



   @Query("SELECT s FROM Calendar s WHERE (s.schDiv = :schDiv AND s.schWriter.empCode = :empCode) OR (s.schDiv = '부서' AND s.dept.deptCode = :deptCode) OR s.schDiv = '전체'")
   List<Calendar> findByAllSchedulesWithEmpCodeAndSchDiv(@Param("empCode") Long
    empCode,
         @Param("schDiv") String schDiv);



      // @EntityGraph(attributePaths = {"schWriter"})
      // Calendar findBySchWriter();

      
}
