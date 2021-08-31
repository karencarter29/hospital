package com.example.clinic.PatientService;

import com.example.clinic.Model.Shift;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;


public class DoctorClient extends Client {

    private static final String SERVICE_PATH = "/Patient/shift/";


    public DoctorClient() {
        super(SERVICE_PATH);
    }

    public Shift addShift(Shift shift) //@RequestBody shift
    {
        return post("/add", shift, Shift.class);
    }
}
