package com.example.clinic.Controllers;
import com.example.clinic.Model.Shift;
import com.example.clinic.PatientService.DoctorClient;
import com.example.clinic.Services.ShiftService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/doctorshift")
@AllArgsConstructor
public class ShiftController {
    //private DoctorClient doctorClient;
    private ShiftService shiftService;

    @PostMapping
    public Shift saveShift(@RequestBody Shift shift) {
        return shiftService.saveShift(shift);
    }
}
