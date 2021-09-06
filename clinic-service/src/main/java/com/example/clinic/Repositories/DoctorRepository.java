package com.example.clinic.Repositories;


import com.example.clinic.DTO.DoctorDTO;
import com.example.clinic.Model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import java.util.UUID;


@Repository
public interface DoctorRepository extends JpaRepository<Doctor, UUID> {
}
