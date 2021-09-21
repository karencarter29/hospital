package com.example.clinic.services;


import com.example.clinic.dto.AppointmentForDoctor;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name="feignAppointmentService", url="http://10.186.0.4:8082/appointment")
public interface AppointmentService {
    @GetMapping("/doctor")
     List<AppointmentForDoctor> getAppointments();
}
