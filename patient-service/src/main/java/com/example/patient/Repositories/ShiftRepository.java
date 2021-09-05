package com.example.patient.Repositories;

import com.example.patient.Model.Shift;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface ShiftRepository extends CrudRepository<Shift, UUID> {
}
