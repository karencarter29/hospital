package com.example.clinic.Repositories;

import com.example.clinic.Model.Procedure;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProcedureRepository extends JpaRepository<Procedure, Integer> {
}
