package com.example.patient.Services;


import com.example.patient.DTO.AppointmentDTO;
import com.example.patient.Model.Appointment;
import com.example.patient.Model.Patient;
import com.example.patient.Model.Shift;
import com.example.patient.Repositories.AppointmentRepository;
import com.example.patient.Repositories.PatientRepository;
import com.example.patient.Repositories.ShiftRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.*;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class AppointmentService {
    private PatientRepository patientRepository;
    private AppointmentRepository appointmentRepository;
    private ShiftRepository shiftRepository;
    private ModelMapper modelMapper;

    public Appointment saveAppointment(AppointmentDTO appointment) throws ParseException {//@RequestBody for app
        Appointment app = convertToEntity(appointment);
        appointmentRepository.saveApp(app.getShift().getId(), app.getPatient().getId());
        return app;
    }

    public List<AppointmentDTO> appointments() {
        List<Appointment> appointmentList = (List<Appointment>) appointmentRepository.findAll();
        return appointmentList.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public Appointment updateAppointment(AppointmentDTO newApp) throws ParseException {//@RequestBody app
        return saveAppointment(newApp);
    }

    public void deleteAppointment(int shiftId, int patientId) {//@PathVariable id
        appointmentRepository.deleteApp(shiftId, patientId);
    }


    private AppointmentDTO convertToDto(Appointment appointment) {
        return modelMapper.map(appointment, AppointmentDTO.class);
    }

    private Appointment convertToEntity(AppointmentDTO appointmentDTO) throws ParseException {
        Appointment appointment = modelMapper.map(appointmentDTO, Appointment.class);

        if (appointmentDTO.getShift().getId() != 0 && appointmentDTO.getPatient().getId() != 0) {
            Shift shift = shiftRepository.findById(appointmentDTO.getShift().getId()).get();
            Patient patient = patientRepository.findById(appointmentDTO.getPatient().getId()).get();
            appointment.setPatient(patient);
        }
        return appointment;
    }
}