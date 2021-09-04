package com.example.alexthbot.fab.services;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@ConditionalOnProperty(prefix = "gateway", name = "state", havingValue = "false")
public class DoctorServiceMock implements DoctorService{
    @Override
    public List<Doctor> get() {
        return Arrays.asList(
                new Doctor(1L,"Зубной техник"),
                new Doctor(2L,"Нарколог")
        );
    }
}
