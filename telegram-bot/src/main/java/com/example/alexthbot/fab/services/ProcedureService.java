package com.example.alexthbot.fab.services;

import org.springframework.stereotype.Service;

import java.util.List;

public interface ProcedureService {
    public List<Shift> getProceduresById(Long id);
}
