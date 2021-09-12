package com.example.patient.Repositories;

import com.example.patient.Model.Shift;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

public interface ShiftRepository extends JpaRepository<Shift, UUID> {
    @Query(value = "SELECT * FROM Shift WHERE doctor_id=:doctor.id", nativeQuery = true)
    @Transactional
    @Modifying
    List<Shift> getShift(@Param("doctor.id") UUID doctor_id);

    @Query(value="SELECT * FROM Shift where start_time between :start.time and :end.time", nativeQuery = true)
    @Transactional
    @Modifying
    List<Shift> forStartTime(@Param("start.time")LocalTime start_time, @Param("end.time")LocalTime end_time);

    @Query(value="SELECT * FROM Shift where end_time between :start.time and :end.time", nativeQuery = true)
    @Transactional
    @Modifying
    List<Shift> forEndTime(@Param("start.time")LocalTime start_time, @Param("end.time")LocalTime end_time);
}
