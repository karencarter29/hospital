package com.example.alexthbot.fab.services.api;

import com.example.alexthbot.fab.services.api.entities.Shift;

import java.util.List;

public interface DateService {
    public List<Shift> getData(Long id);
}
