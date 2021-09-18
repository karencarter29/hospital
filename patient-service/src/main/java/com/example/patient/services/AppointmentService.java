package com.example.patient.services;
import com.example.patient.dto.AppointmentDTO;
import com.example.patient.dto.AppointmentForDoctor;
import com.example.patient.model.Appointment;
import com.example.patient.model.Patient;
import com.example.patient.model.Shift;
import com.example.patient.repositories.AppointmentRepository;
import com.example.patient.repositories.PatientRepository;
import com.example.patient.repositories.ShiftRepository;
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

    public List<AppointmentForDoctor> getAppointments() {
        List<Appointment> appointments = (List<Appointment>)appointmentRepository.findAll();
        List<AppointmentForDoctor> appointmentForDoctorList = new LinkedList<>();
        AppointmentForDoctor appointmentForDoctor;
        for(Appointment a: appointments) {
            appointmentForDoctor = new AppointmentForDoctor(a.getPatient().getFirstName(), a.getPatient().getLastName(),
                    a.getPatient().getPhoneNumber(), a.getShift().getSpecialityName(),
                    a.getShift().getStartTime(), a.getShift().getEndTime(), a.getShift().getDate(),
                    a.getShift().getProcedureName());
            appointmentForDoctorList.add(appointmentForDoctor);
        }
        return appointmentForDoctorList;
    }

    public List<AppointmentDTO> appointments(UUID patientId) {
        List<Appointment> appointmentList = appointmentRepository.getAppointments(patientId);
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