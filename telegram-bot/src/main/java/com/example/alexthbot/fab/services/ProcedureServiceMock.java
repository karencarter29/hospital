package com.example.alexthbot.fab.services;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@ConditionalOnProperty(prefix = "gateway", name = "state", havingValue = "false")
public class ProcedureServiceMock implements ProcedureService{

    @Override
    public List<Procedure> getProcedures() {
        return Arrays.asList(
                new Procedure(1L,"Вырвать зуб"),
                new Procedure(2L,"Отбелить зубы")
        );
    }
}
