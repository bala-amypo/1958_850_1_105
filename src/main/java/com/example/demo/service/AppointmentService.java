package com.example.demo.service;

import com.example.demo.dto.AppointmentDTO;
import java.util.List;

public interface AppointmentService {

    AppointmentDTO createAppointment(AppointmentDTO dto);

    List<AppointmentDTO> getAppointmentsForHost(long hostId);

    List<AppointmentDTO> getAppointmentsForVisitor(long visitorId);
}
