package com.example.clinic.Controllers;
import com.example.clinic.DTO.DoctorDTO;
import com.example.clinic.DTO.SpecialityDTO;
import com.example.clinic.Model.Doctor;
import com.example.clinic.Services.DoctorService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping("/doctor")
@RestController
@AllArgsConstructor
public class DoctorController {

    private DoctorService doctorService;

    @PostMapping("/{specialityId}")
    public Doctor saveDoctor(@RequestBody DoctorDTO doctor, @PathVariable UUID specialityId) {
        return doctorService.saveDoctor(doctor, specialityId);
    }

    @GetMapping
    public List<DoctorDTO> getDoctors() {
        return doctorService.getDoctors();
    }

    @PutMapping("/{specialityId}")
    public Doctor updateDoctor(@RequestBody  DoctorDTO doctor, @PathVariable UUID specialityId) {
        return doctorService.updateDoctor(doctor, specialityId);
    }

    @DeleteMapping("/{id}")
    public void deleteDoctor(@PathVariable UUID id) {
        doctorService.deleteDoctor(id);
    }

}
