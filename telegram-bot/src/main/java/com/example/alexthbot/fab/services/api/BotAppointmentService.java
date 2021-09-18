package com.example.alexthbot.fab.services.api;

import com.example.alexthbot.fab.database.user.model.BotAppointment;
import com.example.alexthbot.fab.services.api.entities.Appointment;
import org.springframework.http.HttpHeaders;

import java.util.List;

public interface BotAppointmentService {
    public HttpHeaders postAppointment(BotAppointment botAppointment);

    public List<Appointment> getAppointments();
}
