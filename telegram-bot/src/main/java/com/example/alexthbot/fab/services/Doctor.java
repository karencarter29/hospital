package com.example.alexthbot.fab.services;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Doctor {
    private UUID id;
    private UUID specialityId;
    private String phoneNumber;
    private String name;

    public Doctor(UUID id, String name) {
        this.id = id;
        this.name = name;
    }
}


