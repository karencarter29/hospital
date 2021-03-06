package com.example.patient.Controllers;

import com.example.patient.Services.ProcedureService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/patientproc")
@AllArgsConstructor
public class ProcedureController {
    private ProcedureService procedureService;
    @GetMapping
    public List<String> getProcedureNames(){
        return procedureService.getProcedures();
    }
}
