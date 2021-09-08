package com.example.alexthbot.fab.services;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Appointment {
    Shift shift;
    Long patientId;
    Condition condition;
}

