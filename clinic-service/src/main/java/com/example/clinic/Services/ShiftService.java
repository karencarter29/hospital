package com.example.clinic.Services;

import com.example.clinic.DTO.ShiftDTO;
import com.example.clinic.Model.Shift;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@FeignClient(name = "feignShiftService", url = "http://localhost:8082/shift")
public interface ShiftService {
    @PostMapping
    public Shift saveShift(@RequestBody ShiftDTO shift);

    @GetMapping
    public List<ShiftDTO> getShifts();

    @PutMapping
    public Shift updateShift(@RequestBody ShiftDTO shift);

    @DeleteMapping("/{id}")
    public void deleteShift(@PathVariable UUID id);

}
