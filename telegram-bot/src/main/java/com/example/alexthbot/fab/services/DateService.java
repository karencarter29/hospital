package com.example.alexthbot.fab.services;

import com.example.alexthbot.fab.services.entities.Shift;

import java.util.List;

public interface DateService {
    public List<Shift> getData(Long id);
}
