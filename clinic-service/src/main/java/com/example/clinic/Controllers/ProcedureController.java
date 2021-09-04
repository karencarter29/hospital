package com.example.clinic.Controllers;

import com.example.clinic.DTO.ProcedureDTO;
import com.example.clinic.Model.Procedure;
import com.example.clinic.Model.Speciality;
import com.example.clinic.Services.ProcedureService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/procedure")
public class ProcedureController {
    private ProcedureService procedureService;

    @PostMapping
    public Procedure addProcedure(@RequestBody Procedure procedure, Speciality speciality) {
        return procedureService.addProcedure(procedure, speciality);
    }

    @GetMapping
    public List<ProcedureDTO> getProcedure() {
        return procedureService.getProcedures();
    }


    @PutMapping
    public Procedure updateProcedure(@RequestBody Procedure newProcedure, Speciality speciality) {
        return procedureService.updateProcedure(newProcedure, speciality);
    }

    @GetMapping("/procedures")
    public List<String> getProcedures() {
        return procedureService.getProcedureNames();
    }

    @DeleteMapping("/delete/{id}")
    public void deleteProcedure(@PathVariable int id) {
        procedureService.deleteProcedure(id);
    }
}
