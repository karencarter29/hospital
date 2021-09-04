package com.example.clinic.Controllers;

import com.example.clinic.DTO.ShiftDTO;
import com.example.clinic.Model.Shift;
import com.example.clinic.Services.ShiftService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doctorshift")
@AllArgsConstructor
public class ShiftController {
    private ShiftService shiftService;

    @PostMapping
    public Shift saveShift(@RequestBody Shift shift) {
        return shiftService.saveShift(shift);
    }

    @GetMapping
    public List<ShiftDTO> getShifts() {
        return shiftService.getShifts();
    }

    @PutMapping
    public Shift updateShift(@RequestBody Shift shift) {
        return shiftService.updateShift(shift);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteShift(@PathVariable int id) {
        shiftService.deleteShift(id);
    }
}
