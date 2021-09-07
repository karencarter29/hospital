package com.example.alexthbot.fab.services;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@ConditionalOnProperty(prefix = "gateway", name = "state", havingValue = "false")
public class TimeServiceMock implements TimeForBook{
    @Override
    public List<String> getTime() {
        return Arrays.asList ("10.00","12.00","14.00");
    }
}
