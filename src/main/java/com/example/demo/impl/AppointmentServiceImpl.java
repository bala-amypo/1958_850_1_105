package com.example.demo.service.impl;

import com.example.demo.dto.AppointmentDTO;
import com.example.demo.entity.Appointment;
import com.example.demo.entity.Host;
import com.example.demo.entity.Visitor;
import com.example.demo.repository.AppointmentRepository;
import com.example.demo.repository.HostRepository;
import com.example.demo.repository.VisitorRepository;
import com.example.demo.service.AppointmentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final HostRepository hostRepository;
    private final VisitorRepository visitorRepository;

    public AppointmentServiceImpl(AppointmentRepository appointmentRepository,
                                  HostRepository hostRepository,
                                  VisitorRepository visitorRepository) {
        this.appointmentRepository = appointmentRepository;
        this.hostRepository = hostRepository;
        this.visitorRepository = visitorRepository;
    }

    @Override
    public AppointmentDTO createAppointment(AppointmentDTO dto) {
        Appointment appointment = fromDto(dto);
        Appointment saved = appointmentRepository.save(appointment);
        return toDto(saved);
    }

    @Override
    public List<AppointmentDTO> getAllAppointments() {
        return appointmentRepository.findAll().stream().map(this::toDto).collect(Collectors.toList());
    }

    @Override
    public AppointmentDTO getAppointmentById(Long id) {
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Appointment not found"));
        return toDto(appointment);
    }

    @Override
    public List<AppointmentDTO> getAppointmentsByHostId(Long hostId) {
        return appointmentRepository.findByHostId(hostId).stream().map(this::toDto).collect(Collectors.toList());
    }

    @Override
    public List<AppointmentDTO> getAppointmentsByVisitorId(Long visitorId) {
        return appointmentRepository.findByVisitorId(visitorId).stream().map(this::toDto).collect(Collectors.toList());
    }

    @Override
    public AppointmentDTO updateAppointment(Long id, AppointmentDTO dto) {
        Appointment existing = appointmentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Appointment not found"));
        existing = fromDto(dto, existing);
        Appointment saved = appointmentRepository.save(existing);
        return toDto(saved);
    }

    @Override
    public void deleteAppointment(Long id) {
        appointmentRepository.deleteById(id);
    }

    private Appointment fromDto(AppointmentDTO dto) {
        return fromDto(dto, new Appointment());
    }

    private Appointment fromDto(AppointmentDTO dto, Appointment appointment) {
        Host host = hostRepository.findById(dto.getHostId())
                .orElseThrow(() -> new IllegalArgumentException("Host not found"));
        Visitor visitor = visitorRepository.findById(dto.getVisitorId())
                .orElseThrow(() -> new IllegalArgumentException("Visitor not found"));

        appointment.setHost(host);
        appointment.setVisitor(visitor);
        appointment.setAppointmentDate(dto.getAppointmentDate());
        appointment.setPurpose(dto.getPurpose());
        appointment.setStatus(dto.getStatus());
        return appointment;
    }

    private AppointmentDTO toDto(Appointment a) {
        AppointmentDTO dto = new AppointmentDTO();
        dto.setId(a.getId());
        dto.setHostId(a.getHost().getId());
        dto.setVisitorId(a.getVisitor().getId());
        dto.setAppointmentDate(a.getAppointmentDate());
        dto.setPurpose(a.getPurpose());
        dto.setStatus(a.getStatus());
        return dto;
    }
}
