package com.example.alexthbot.fab.services;

import com.example.alexthbot.fab.database.user.model.BotAppointment;
import com.example.alexthbot.fab.services.entities.Appointment;

import java.util.List;

public interface BotAppointmentService {
    public String postAppointment(BotAppointment botAppointment);
    public List<Appointment> getAppointments();
}
