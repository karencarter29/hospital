package com.example.alexthbot.fab.services;

import org.springframework.stereotype.Service;

import java.util.List;

public interface ProcedureService {
    public List<Procedure> getProceduresOfTooth();
    public List<Procedure> getProceduresOfDrags();
}
