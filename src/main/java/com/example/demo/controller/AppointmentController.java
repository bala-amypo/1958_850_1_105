package com.example.demo.controller;
import com.example.demo.dto.ApiResponse;

import com.example.demo.dto.AppointmentDTO;
import com.example.demo.entity.Appointment;
import com.example.demo.service.AppointmentService;
import com.example.demo.util.DTOConverter;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/appointments")
@CrossOrigin(origins = "*")
public class AppointmentController {
    
    @Autowired
    private AppointmentService appointmentService;

    @PostMapping("/create/{visitorId}/{hostId}")
    public ResponseEntity<ApiResponse> createAppointment(
            @PathVariable Long visitorId, 
            @PathVariable Long hostId, 
            @Valid @RequestBody AppointmentDTO appointmentDTO) {
        Appointment appointment = new Appointment();
        appointment.setAppointmentDate(appointmentDTO.getAppointmentDate());
        appointment.setPurpose(appointmentDTO.getPurpose());
        appointment.setStatus(appointmentDTO.getStatus());
        Appointment saved = appointmentService.createAppointment(visitorId, hostId, appointment);
        return ResponseEntity.ok(new ApiResponse(true, "Appointment created", DTOConverter.toAppointmentDTO(saved)));
    }

    @GetMapping
    public ResponseEntity<ApiResponse> getAllAppointments() {
        List<AppointmentDTO> appointments = DTOConverter.toAppointmentDTOList(appointmentService.getAllAppointments());
        return ResponseEntity.ok(new ApiResponse(true, "Appointments retrieved", appointments));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getAppointmentById(@PathVariable Long id) {
        Appointment appointment = appointmentService.getAppointmentById(id);
        return ResponseEntity.ok(new ApiResponse(true, "Appointment found", DTOConverter.toAppointmentDTO(appointment)));
    }

    @GetMapping("/host/{hostId}")
    public ResponseEntity<ApiResponse> getAppointmentsByHostId(@PathVariable Long hostId) {
        List<AppointmentDTO> appointments = DTOConverter.toAppointmentDTOList(appointmentService.getAppointmentsByHostId(hostId));
        return ResponseEntity.ok(new ApiResponse(true, "Host appointments retrieved", appointments));
    }

    @GetMapping("/visitor/{visitorId}")
    public ResponseEntity<ApiResponse> getAppointmentsByVisitorId(@PathVariable Long visitorId) {
        List<AppointmentDTO> appointments = DTOConverter.toAppointmentDTOList(appointmentService.getAppointmentsByVisitorId(visitorId));
        return ResponseEntity.ok(new ApiResponse(true, "Visitor appointments retrieved", appointments));
    }
}
