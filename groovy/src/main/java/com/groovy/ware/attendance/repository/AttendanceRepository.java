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

    @Query("SELECT a FROM Attendance a WHERE a.employee.empCode = :empCode AND a.attDate = CURRENT_DATE")
    Attendance findOneAttendance(@Param("empCode") Long empCode);

    
}
