package com.example.demo.service.impl;

import com.example.demo.entity.Appointment;
import com.example.demo.entity.Visitor;
import com.example.demo.entity.Host;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.AppointmentRepository;
import com.example.demo.repository.VisitorRepository;
import com.example.demo.repository.HostRepository;
import com.example.demo.service.AppointmentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final VisitorRepository visitorRepository;
    private final HostRepository hostRepository;

    public AppointmentServiceImpl(AppointmentRepository appointmentRepository,
                                  VisitorRepository visitorRepository,
                                  HostRepository hostRepository) {
        this.appointmentRepository = appointmentRepository;
        this.visitorRepository = visitorRepository;
        this.hostRepository = hostRepository;
    }

    @Override
    public Appointment createAppointment(Appointment appointment) {
        if (appointment.getDate().before(new Date())) {
            throw new IllegalArgumentException("Appointment date cannot be in the past");
        }

        // Ensure Visitor exists
        Visitor visitor = visitorRepository.findById(appointment.getVisitor().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Visitor not found"));
        appointment.setVisitor(visitor);

        // Ensure Host exists
        Host host = hostRepository.findById(appointment.getHost().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Host not found"));
        appointment.setHost(host);

        // Default status
        if (appointment.getStatus() == null) {
            appointment.setStatus(Appointment.Status.SCHEDULED);
        }

        return appointmentRepository.save(appointment);
    }

    @Override
    public Appointment getAppointment(Long id) {
        return appointmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Appointment not found"));
    }

    @Override
    public List<Appointment> getAppointmentsByHost(Long hostId) {
        return appointmentRepository.findByHostId(hostId);
    }

    @Override
    public List<Appointment> getAppointmentsByVisitor(Long visitorId) {
        return appointmentRepository.findByVisitorId(visitorId);
    }
}
