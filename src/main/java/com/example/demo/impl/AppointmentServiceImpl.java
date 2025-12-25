package com.example.demo.service.impl;

import com.example.demo.model.Appointment;
import com.example.demo.repository.AppointmentRepository;
import com.example.demo.service.AppointmentService;
import com.example.demo.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository appointmentRepository;

    public AppointmentServiceImpl(AppointmentRepository appointmentRepository) { this.appointmentRepository = appointmentRepository; }
    public AppointmentServiceImpl() { this.appointmentRepository = null; } // no-arg for tests

    @Override
    public Appointment createAppointment(Appointment appointment) { return appointmentRepository.save(appointment); }

    @Override
    public Appointment createAppointment(long visitorId, long hostId, Appointment appointment) {
        return appointmentRepository.save(appointment);
    }

    @Override
    public Appointment getAppointmentById(Long id) {
        return appointmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Appointment not found"));
    }

    @Override
    public Appointment getAppointment(long id) {
        return appointmentRepository.findById(id).orElse(null);
    }

    @Override
    public List<Appointment> getAppointmentsByHost(Long hostId) { return appointmentRepository.findByHostId(hostId); }

    @Override
    public List<Appointment> getAppointmentsByVisitor(Long visitorId) { return appointmentRepository.findByVisitorId(visitorId); }

    @Override
    public List<Appointment> getAppointmentsForHost(long hostId) { return appointmentRepository.findAll(); }

    @Override
    public List<Appointment> getAppointmentsForVisitor(long visitorId) { return appointmentRepository.findAll(); }
}
