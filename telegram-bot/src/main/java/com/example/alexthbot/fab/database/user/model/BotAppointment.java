package com.example.alexthbot.fab.database.user.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BotAppointment {
    private String id;
    private String doctor;
    private String procedure;
    private String date;
    private String time;

    public BotAppointment(String doctor, String procedure, String date, String time) {
        this.doctor = doctor;
        this.procedure = procedure;
        this.date = date;
        this.time = time;
    }
}
