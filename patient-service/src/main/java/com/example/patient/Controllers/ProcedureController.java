package com.example.patient.Controllers;

import com.example.patient.Services.ProcedureService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/patientproc")
public class ProcedureController {
    private ProcedureService procedureService;
    @GetMapping("/procedures")
    public List<String> getProcedureNames(){
        return procedureService.getProcedures();
    }
}
