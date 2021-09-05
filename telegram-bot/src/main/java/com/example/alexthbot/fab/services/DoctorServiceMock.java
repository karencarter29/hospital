package com.example.alexthbot.fab.services;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
@ConditionalOnProperty(prefix = "gateway", name = "state", havingValue = "false")
public class DoctorServiceMock implements DoctorService{
    @Override
    public List<Doctor> get() {
        return Arrays.asList(
                new Doctor(UUID.randomUUID(),"Зубной техник"),
                new Doctor(UUID.randomUUID(),"Нарколог")
        );
    }
}
