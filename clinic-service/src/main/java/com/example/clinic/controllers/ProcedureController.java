package com.example.clinic.controllers;

import com.example.clinic.dto.ProcedureDTO;
import com.example.clinic.model.Procedure;
import com.example.clinic.services.ProcedureService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/procedure")
public class ProcedureController {
    private ProcedureService procedureService;

    @PostMapping("{specialityId}")
    public Procedure addProcedure(@RequestBody ProcedureDTO procedure, @PathVariable UUID specialityId) {
        return procedureService.addProcedure(procedure, specialityId);
    }

    @GetMapping
    public List<ProcedureDTO> getProcedure() {
        return procedureService.getProcedures();
    }


    @PutMapping("/{specialityId}")
    public Procedure updateProcedure(@RequestBody ProcedureDTO newProcedure, @PathVariable UUID specialityId) {
        return procedureService.updateProcedure(newProcedure, specialityId);
    }

    @GetMapping("/procedures")
    public List<String> getProcedures() {
        return procedureService.getProcedureNames();
    }

    @DeleteMapping("/{id}")
    public void deleteProcedure(@PathVariable UUID id) {
        procedureService.deleteProcedure(id);
    }
}
