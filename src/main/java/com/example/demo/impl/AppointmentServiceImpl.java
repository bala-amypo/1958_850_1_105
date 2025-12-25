package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.*;
import com.example.demo.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class AppointmentServiceImpl implements AppointmentService {
    
    AppointmentRepository appointmentRepository;
    VisitorRepository visitorRepository;
    HostRepository hostRepository;

    public AppointmentServiceImpl() {}

    @Autowired
    public AppointmentServiceImpl(AppointmentRepository appointmentRepository, 
                                 VisitorRepository visitorRepository, 
                                 HostRepository hostRepository) {
        this.appointmentRepository = appointmentRepository;
        this.visitorRepository = visitorRepository;
        this.hostRepository = hostRepository;
    }

    @Override
    public Appointment createAppointment(Long visitorId, Long hostId, Appointment appointment) {
        Visitor visitor = visitorRepository.findById(visitorId)
            .orElseThrow(() -> new ResourceNotFoundException("Visitor not found"));
        Host host = hostRepository.findById(hostId)
            .orElseThrow(() -> new ResourceNotFoundException("Host not found"));

        appointment.setVisitor(visitor);
        appointment.setHost(host);
        if (appointment.getAppointmentDate().isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("appointmentDate cannot be past");
        }
        if (appointment.getStatus() == null) {
            appointment.setStatus("SCHEDULED");
        }
        return appointmentRepository.save(appointment);
    }

    @Override
    public Appointment getAppointment(Long id) {
        return appointmentRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Appointment not found"));
    }

    @Override
    public List<Appointment> getAppointmentsForHost(Long hostId) {
        return appointmentRepository.findByHostId(hostId);
    }

    @Override
    public List<Appointment> getAppointmentsForVisitor(Long visitorId) {
        return appointmentRepository.findByVisitorId(visitorId);
    }
}
