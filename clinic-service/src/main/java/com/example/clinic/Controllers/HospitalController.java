package com.example.clinic.Controllers;

import com.example.clinic.DTO.HospitalDTO;
import com.example.clinic.Model.Hospital;
import com.example.clinic.Services.HospitalService;
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
