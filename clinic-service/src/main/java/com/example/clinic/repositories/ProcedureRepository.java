package com.example.clinic.repositories;

import com.example.clinic.model.Procedure;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProcedureRepository extends JpaRepository<Procedure, UUID> {

}
