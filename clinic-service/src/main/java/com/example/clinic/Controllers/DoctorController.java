package com.example.clinic.Controllers;


import com.example.clinic.DTO.DoctorDTO;
import com.example.clinic.Model.Doctor;
import com.example.clinic.Model.Speciality;
import com.example.clinic.Services.DoctorService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api")
@RestController
@AllArgsConstructor
public class DoctorController {

    private DoctorService doctorService;

    @PostMapping
    public Doctor saveDoctor(@RequestBody Doctor doctor, Speciality speciality) {
        return doctorService.saveDoctor(doctor, speciality);
    }

    @GetMapping
    public List<DoctorDTO> getDoctors() {
        return doctorService.getDoctors();
    }

    @PutMapping
    public Doctor updateDoctor(@RequestBody  Doctor doctor, Speciality speciality) {
        return doctorService.updateDoctor(doctor, speciality);
    }

    @DeleteMapping("/{id}")
    public void deleteDoctor(@PathVariable int id) {
        doctorService.deleteDoctor(id);
    }

}
