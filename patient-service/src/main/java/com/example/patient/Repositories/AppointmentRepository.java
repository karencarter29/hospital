package com.example.patient.Repositories;

import com.example.patient.Model.Appointment;
import com.example.patient.Model.RelationShipPK;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

public interface AppointmentRepository extends CrudRepository<Appointment, RelationShipPK> {


    @Query(value="INSERT INTO Appointment(shift_id, patient_id) values(:shift.id, :patient.id)", nativeQuery = true)
    @Transactional
    @Modifying
    void saveApp(@Param("shift.id") UUID shift_id, @Param("patient.id") UUID patient_id);

    @Query(value="DELETE FROM Appointment a where a.shift_id=:shift.id and a.patient_id=:patient.id", nativeQuery = true)
    @Transactional
    @Modifying
    void deleteApp(@Param("shift.id") UUID shift_id, @Param("patient.id") UUID patient_id);

    @Query(value="UPDATE Appointment SET shift_id=:shift.id WHERE patient_id=:patient.id", nativeQuery = true)
    @Transactional
    @Modifying
    void updateApp(@Param("shift.id") UUID shift_id, @Param("patient.id") UUID patient_id);
}
