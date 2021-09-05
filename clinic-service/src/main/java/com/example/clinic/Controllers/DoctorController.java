package com.example.clinic.Controllers;
import com.example.clinic.DTO.DoctorDTO;
import com.example.clinic.Model.Doctor;
import com.example.clinic.Model.Speciality;
import com.example.clinic.Services.DoctorService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/doctor")
@RestController
@AllArgsConstructor
public class DoctorController {

    private DoctorService doctorService;

    @PostMapping("/{specialityId}")
    public Doctor saveDoctor(@RequestBody DoctorDTO doctor, @PathVariable int specialityId) {
        return doctorService.saveDoctor(doctor, specialityId);
    }

    @GetMapping
    public List<DoctorDTO> getDoctors() {
        return doctorService.getDoctors();
    }

    @PutMapping("/{specialityId}")
    public Doctor updateDoctor(@RequestBody  DoctorDTO doctor, @PathVariable int specialityId) {
        return doctorService.updateDoctor(doctor, specialityId);
    }

    @DeleteMapping("/{id}")
    public void deleteDoctor(@PathVariable int id) {
        doctorService.deleteDoctor(id);
    }
}
