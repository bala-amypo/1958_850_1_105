package com.example.demo.controller;

import com.example.demo.dto.AppointmentDTO;
import com.example.demo.dto.ApiResponse;
import com.example.demo.entity.Appointment;
import com.example.demo.service.AppointmentService;
import com.example.demo.util.DTOConverter;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/appointments")
@Tag(name = "Appointments", description = "Appointment management APIs")
@SecurityRequirement(name = "Bearer Authentication")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @PostMapping("/create/{visitorId}/{hostId}")
    @Operation(summary = "Create a new appointment")
    public ResponseEntity<ApiResponse> createAppointment(
            @PathVariable Long visitorId,
            @PathVariable Long hostId,
            @Valid @RequestBody AppointmentDTO appointmentDTO) {

        Appointment appointment = new Appointment();
        // DTO and entity both use LocalDateTime
        appointment.setAppointmentDate(appointmentDTO.getAppointmentDate());
        appointment.setPurpose(appointmentDTO.getPurpose());
        appointment.setStatus("SCHEDULED");

        Appointment saved = appointmentService.createAppointment(visitorId, hostId, appointment);
        AppointmentDTO result = DTOConverter.toAppointmentDTO(saved);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse(true, "Appointment created successfully", result));
    }

    @GetMapping
    @Operation(summary = "Get all appointments")
    public ResponseEntity<ApiResponse> getAllAppointments() {
        List<AppointmentDTO> appointments =
                DTOConverter.toAppointmentDTOList(appointmentService.getAllAppointments());
        return ResponseEntity.ok(new ApiResponse(true, "Appointments retrieved successfully", appointments));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get appointment by ID")
    public ResponseEntity<ApiResponse> getAppointmentById(@PathVariable Long id) {
        AppointmentDTO result = DTOConverter.toAppointmentDTO(appointmentService.getAppointmentById(id));
        return ResponseEntity.ok(new ApiResponse(true, "Appointment retrieved successfully", result));
    }

    @GetMapping("/host/{hostId}")
    @Operation(summary = "Get appointments by host ID")
    public ResponseEntity<ApiResponse> getAppointmentsByHostId(@PathVariable Long hostId) {
        List<AppointmentDTO> appointments =
                DTOConverter.toAppointmentDTOList(appointmentService.getAppointmentsByHostId(hostId));
        return ResponseEntity.ok(new ApiResponse(true, "Host appointments retrieved successfully", appointments));
    }

    @GetMapping("/visitor/{visitorId}")
    @Operation(summary = "Get appointments by visitor ID")
    public ResponseEntity<ApiResponse> getAppointmentsByVisitorId(@PathVariable Long visitorId) {
        List<AppointmentDTO> appointments =
                DTOConverter.toAppointmentDTOList(appointmentService.getAppointmentsByVisitorId(visitorId));
        return ResponseEntity.ok(new ApiResponse(true, "Visitor appointments retrieved successfully", appointments));
    }
}
