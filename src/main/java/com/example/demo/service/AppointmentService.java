package com.example.demo.service;

import com.example.demo.entity.Appointment;
import java.util.List;

public interface AppointmentService {
    List<Appointment> getAppointmentsForVisitor(long visitorId);
    Appointment createAppointment(Appointment appointment);
}
