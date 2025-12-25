package com.example.demo.service;

import com.example.demo.model.Appointment;
import java.util.List;

public interface AppointmentService {

    Appointment createAppointment(Appointment appointment);

    Appointment getAppointmentById(Long id);

    List<Appointment> getAppointmentsByHost(Long hostId);

    List<Appointment> getAppointmentsByVisitor(Long visitorId);
}
