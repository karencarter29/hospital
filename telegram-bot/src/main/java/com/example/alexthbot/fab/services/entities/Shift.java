package com.example.alexthbot.fab.services.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Shift {
    String id;
    String doctorId;
    Procedure procedure;
    String startTime;
    String endTime;
    String date;
    String procedureName;
}
