package com.example.clinic.Controllers;

import com.example.clinic.DTO.HospitalDTO;
import com.example.clinic.Model.Hospital;
import com.example.clinic.Services.HospitalService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hospital")
@AllArgsConstructor
public class HospitalController {
    private HospitalService hospitalService;

    @PostMapping
    public Hospital saveHospital(@RequestBody Hospital hospital) {
        return hospitalService.addHospital(hospital);
    }

    @GetMapping
    public List<HospitalDTO> getHospitals() {
        return hospitalService.getHospitals();
    }

    @PutMapping
    public Hospital updateHospital(Hospital hospital) {
        return hospitalService.updateHospital(hospital);
    }

    @DeleteMapping("/delet/{id}")
    public void deleteHospital(@PathVariable int id) {
        hospitalService.deleteHospital(id);
    }
}
