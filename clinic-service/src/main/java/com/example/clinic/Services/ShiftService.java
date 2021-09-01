package com.example.clinic.Services;

import com.example.clinic.Model.Shift;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name="feignShiftService", url="http://localhost:8082/shift")
public interface ShiftService {
    @PostMapping
    public Shift saveShift(@RequestBody Shift shift);
}
