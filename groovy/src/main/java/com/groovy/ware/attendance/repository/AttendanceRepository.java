package com.groovy.ware.attendance.repository;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.groovy.ware.attendance.entity.Attendance;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {

   
    
    @Query(value = "SELECT * FROM GRV_ATTENDENCE  WHERE EMP_CODE = :empCode AND TRUNC(ATT_DATE) = TRUNC(SYSDATE)", nativeQuery = true)
    Attendance findOneAttendance(@Param("empCode") Long empCode);
    
    /* 전체 직원 근태 조회 영주 */
    Page<Attendance> findAll(Pageable pageable);
    

}
