package com.example.clinic.repositories;


import com.example.clinic.model.RelationShipPK;
import com.example.clinic.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

public interface RoomRepository extends JpaRepository<Room, RelationShipPK> {
    @Query(value="INSERT INTO Room(room_number, hospital_id, doctor_id) values(:roomNumber, :hospital.id, :doctor.id)", nativeQuery = true)
    @Transactional
    @Modifying
    void saveRoom(@Param("hospital.id") UUID hospitalId, @Param("doctor.id") UUID doctorId, @Param("roomNumber") String roomNumber);

    @Query(value="DELETE FROM Room r where r.hospital_id=:hospital.id and r.doctor_id=:doctor.id", nativeQuery = true)
    @Transactional
    @Modifying
    void deleteRoom(@Param("hospital.id") UUID hospitalId, @Param("doctor.id") UUID doctorId);

    @Query(value="UPDATE Room SET room_number=:roomNumber WHERE hospital_id=:hospital.id AND doctor_id=:doctor.id", nativeQuery = true)
    @Transactional
    @Modifying
    void updateRoom(@Param("hospital.id") UUID hospitalId, @Param("doctor.id") UUID doctorId, @Param("roomNumber") String roomNumber);
}
