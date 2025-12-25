package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.dto.AppointmentDTO;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.*;
import com.example.demo.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

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

    // TEST signature (3 params)
    @Override
    public Appointment createAppointment(Long visitorId, Long hostId, Appointment appointment) {
        Visitor visitor = visitorRepository.findById(visitorId)
            .orElseThrow(() -> new ResourceNotFoundException("Visitor not found"));
        Host host = hostRepository.findById(hostId)
            .orElseThrow(() -> new ResourceNotFoundException("Host not found"));
        appointment.setVisitor(visitor);
        appointment.setHost(host);
        appointment.setStatus("SCHEDULED");
        return appointmentRepository.save(appointment);
    }

    // CONTROLLER methods
    @Override
    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    @Override
    public Appointment getAppointmentById(Long id) {
        return appointmentRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Appointment not found"));
    }

    @Override
    public List<Appointment> getAppointmentsByHostId(Long hostId) {
        return appointmentRepository.findByHostId(hostId);
    }

    @Override
    public List<Appointment> getAppointmentsByVisitorId(Long visitorId) {
        return appointmentRepository.findByVisitorId(visitorId);
    }

    @Override
    public Appointment updateAppointment(Long id, AppointmentDTO dto) {
        Appointment appointment = getAppointmentById(id);
        // Update from DTO
        return appointmentRepository.save(appointment);
    }

    @Override
    public void deleteAppointment(Long id) {
        if (!appointmentRepository.existsById(id)) {
            throw new ResourceNotFoundException("Appointment not found");
        }
        appointmentRepository.deleteById(id);
    }
}
