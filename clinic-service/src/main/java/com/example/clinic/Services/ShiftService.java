package com.example.clinic.Services;

import com.example.clinic.DTO.ShiftDTO;
import com.example.clinic.DTO.SpecialityDTO;
import com.example.clinic.Model.Shift;
import com.example.clinic.Model.Speciality;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "feignShiftService", url = "http://localhost:8082/shift")
public interface ShiftService {
    @PostMapping
    public Shift saveShift(@RequestBody ShiftDTO shift);

    @GetMapping
    public List<ShiftDTO> getShifts();

    @PutMapping
    public Shift updateShift(@RequestBody ShiftDTO shift);

    @DeleteMapping("/{id}")
    public void deleteShift(@PathVariable int id);

}
