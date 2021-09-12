package com.example.clinic.Controllers;

import com.example.clinic.DTO.SpecialityDTO;
import com.example.clinic.Model.Speciality;
import com.example.clinic.Services.SpecialityService;
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
