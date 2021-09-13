package com.example.clinic.controllers;

import com.example.clinic.dto.HospitalDTO;
import com.example.clinic.model.Hospital;
import com.example.clinic.services.HospitalService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/hospital")
@AllArgsConstructor
public class HospitalController {
    private HospitalService hospitalService;

    @PostMapping
    public Hospital saveHospital(@RequestBody HospitalDTO hospital) {
        return hospitalService.addHospital(hospital);
    }

    @GetMapping
    public List<HospitalDTO> getHospitals() {
        return hospitalService.getHospitals();
    }

    @PutMapping
    public Hospital updateHospital(@RequestBody HospitalDTO hospital) {
        return hospitalService.updateHospital(hospital);
    }

    @DeleteMapping("/{id}")
    public void deleteHospital(@PathVariable UUID id) {
        hospitalService.deleteHospital(id);
    }
}
