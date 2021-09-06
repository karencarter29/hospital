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


import java.util.*;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class AppointmentService {
    private AppointmentRepository appointmentRepository;
    private ModelMapper modelMapper;
    private ShiftRepository shiftRepository;
    private PatientRepository patientRepository;

    public Appointment saveAppointment(UUID shiftId, UUID patientId) {
        Shift sh = shiftRepository.findById(shiftId).orElse(null);
        Patient p = patientRepository.findById(patientId).orElse(null);
        Appointment a = new Appointment(sh, p);
        appointmentRepository.saveApp(shiftId, patientId);
        return a;
    }

    public List<AppointmentDTO> appointments() {
        List<Appointment> appointmentList = (List<Appointment>) appointmentRepository.findAll();
        return appointmentList.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public Appointment updateAppointment(UUID shiftId, UUID patientId){
        Shift sh = shiftRepository.findById(shiftId).orElse(null);
        Patient p = patientRepository.findById(patientId).orElse(null);
        Appointment a = new Appointment(sh, p);
        appointmentRepository.updateApp(shiftId, patientId);
        return a;
    }

    public void deleteAppointment(UUID shiftId, UUID patientId) {//@PathVariable id
        appointmentRepository.deleteApp(shiftId,patientId);
    }


    private AppointmentDTO convertToDto(Appointment appointment) {
        return modelMapper.map(appointment, AppointmentDTO.class);
    }
}