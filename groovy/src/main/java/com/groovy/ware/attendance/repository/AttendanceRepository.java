package com.groovy.ware.attendance.repository;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.groovy.ware.attendance.entity.Attendance;
import com.groovy.ware.employee.dto.DepartmentDto;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {

   
    
    @Query(value = "SELECT * FROM GRV_ATTENDENCE a WHERE a.EMP_CODE = :empCode AND TRUNC(a.ATT_DATE) = TRUNC(SYSDATE)", nativeQuery = true)
    Attendance findOneAttendance(@Param("empCode") Long empCode);
    

    
}
