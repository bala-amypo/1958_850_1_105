package com.example.demo.service;

import com.example.demo.model.Appointment;
import java.util.List;

public interface AppointmentService {
    Appointment createAppointment(Appointment appointment);
    Appointment createAppointment(long visitorId, long hostId, Appointment appointment); // overload
    Appointment getAppointmentById(Long id);
    Appointment getAppointment(long id); // for tests
    List<Appointment> getAppointmentsByHost(Long hostId);
    List<Appointment> getAppointmentsByVisitor(Long visitorId);
    List<Appointment> getAppointmentsForHost(long hostId); // for tests
    List<Appointment> getAppointmentsForVisitor(long visitorId); // for tests
}