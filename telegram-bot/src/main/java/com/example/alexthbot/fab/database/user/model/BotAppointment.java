package com.example.alexthbot.fab.database.user.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.time.LocalDateTime;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BotAppointment {
    private Long id;
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
