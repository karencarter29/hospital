package com.example.alexthbot.fab.services;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Doctor {
    private Long id;
    private Long specialityId;
    private String phoneNumber;
    private String name;


    public Doctor(Long specialityId, String phoneNumber, String name) {
        this.specialityId = specialityId;
        this.phoneNumber = phoneNumber;
        this.name = name;
    }
}


