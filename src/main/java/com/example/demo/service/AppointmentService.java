package com.example.demo.service;
import com.example.demo.dto.AppointmentDTO;
import java.util.List;

public interface AppointmentService {
    AppointmentDTO createAppointment(AppointmentDTO appointmentDTO);
    List<AppointmentDTO> getAllAppointments();
    AppointmentDTO getAppointmentById(Long id);
    List<AppointmentDTO> getAppointmentsByHostId(Long hostId);
    List<AppointmentDTO> getAppointmentsByVisitorId(Long visitorId);
    AppointmentDTO updateAppointment(Long id, AppointmentDTO appointmentDTO);
    void deleteAppointment(Long id);
}
