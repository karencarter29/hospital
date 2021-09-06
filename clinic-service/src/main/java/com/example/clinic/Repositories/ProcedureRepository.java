package com.example.clinic.Repositories;

import com.example.clinic.Model.Procedure;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProcedureRepository extends JpaRepository<Procedure, UUID> {

}
