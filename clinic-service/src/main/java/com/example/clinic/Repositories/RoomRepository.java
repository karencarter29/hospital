package com.example.clinic.Repositories;


import com.example.clinic.Model.RelationShipPK;
import com.example.clinic.Model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface RoomRepository extends JpaRepository<Room, RelationShipPK> {
    @Query(value="INSERT INTO Room(hospital_id, doctor_id) values(:hospital.id, :docotr.id)", nativeQuery = true)
    @Transactional
    @Modifying
    void saveRoom(@Param("hospital.id") int hospital_id, @Param("doctor.id") int doctor_id);

    @Query(value="DELETE FROM Room r where r.hospital_id=:hospital.id and r.doctor_id=:docotr.id", nativeQuery = true)
    @Transactional
    @Modifying
    void deleteRoom(@Param("hospital.id") int hospital_id, @Param("doctor.id") int doctor_id);
}
