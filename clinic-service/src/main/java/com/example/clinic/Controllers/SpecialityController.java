package com.example.clinic.Controllers;

import com.example.clinic.DTO.SpecialityDTO;
import com.example.clinic.Model.Speciality;
import com.example.clinic.Services.SpecialityService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/speciality")
public class SpecialityController {
    private SpecialityService specialityService;

    @PostMapping
    public Speciality saveSpeciality(@RequestBody Speciality speciality) {
        return specialityService.saveSpeciality(speciality);
    }

    @GetMapping
    public List<SpecialityDTO> getSpecialityOfDoctor() {
        return specialityService.getSpecialities();
    }

    @PutMapping
    public Speciality updateSpeciality(@RequestBody Speciality speciality) {
        return specialityService.updateSpeciality(speciality);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteSpeciality(@PathVariable int id) {
        specialityService.deleteSpeciality(id);
    }

}