package com.example.patient.Repositories;

import com.example.patient.Model.Patient;
import org.springframework.data.repository.CrudRepository;

public interface PatientRepository extends CrudRepository<Patient, Integer> {
}
