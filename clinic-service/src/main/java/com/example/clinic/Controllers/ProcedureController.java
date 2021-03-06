package com.example.clinic.Controllers;

import com.example.clinic.DTO.ProcedureDTO;
import com.example.clinic.Model.Procedure;
import com.example.clinic.Model.Speciality;
import com.example.clinic.Services.ProcedureService;
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
