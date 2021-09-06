package com.example.clinic.Repositories;


import com.example.clinic.Model.Speciality;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SpecialityRepository extends JpaRepository<Speciality, UUID> {

}
