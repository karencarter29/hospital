package com.example.clinic.controllers;

import com.example.clinic.dto.SpecialityDTO;
import com.example.clinic.model.Speciality;
import com.example.clinic.services.SpecialityService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/speciality")
public class SpecialityController {
    private SpecialityService specialityService;

    @PostMapping
    public Speciality saveSpeciality(@RequestBody SpecialityDTO speciality) {
        return specialityService.saveSpeciality(speciality);
    }

    @GetMapping
    public List<SpecialityDTO> getSpecialityOfDoctor() {
        return specialityService.getSpecialities();
    }

    @PutMapping
    public Speciality updateSpeciality(@RequestBody SpecialityDTO speciality) {
        return specialityService.updateSpeciality(speciality);
    }

    @DeleteMapping("/{id}")
    public void deleteSpeciality(@PathVariable UUID id) {
        specialityService.deleteSpeciality(id);
    }

}
