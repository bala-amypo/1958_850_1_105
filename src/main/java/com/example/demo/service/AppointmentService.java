package com.example.demo.service;

import com.example.demo.entity.Appointment;
import com.example.demo.dto.AppointmentDTO;
import java.util.List;

public interface AppointmentService {
    Appointment createAppointment(Long visitorId, Long hostId, Appointment appointment);
    List<Appointment> getAllAppointments();
    Appointment getAppointmentById(Long id);
    List<Appointment> getAppointmentsByHostId(Long hostId);
    List<Appointment> getAppointmentsByVisitorId(Long visitorId);
    Appointment updateAppointment(Long id, AppointmentDTO appointmentDTO);
    void deleteAppointment(Long id);
}
