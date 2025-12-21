package com.example.demo.controller;

import com.example.demo.dto.ApiResponse;
import com.example.demo.model.Appointment;
import com.example.demo.service.AppointmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {

    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @PostMapping("/{visitorId}/{hostId}")
    public ResponseEntity<ApiResponse> create(@PathVariable Long visitorId,
                                              @PathVariable Long hostId,
                                              @RequestBody Appointment appointment) {

        return new ResponseEntity<>(
                new ApiResponse(true, "Appointment created",
                        appointmentService.createAppointment(visitorId, hostId, appointment)),
                HttpStatus.CREATED
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> get(@PathVariable Long id) {
        return ResponseEntity.ok(
                new ApiResponse(true, "Appointment fetched",
                        appointmentService.getAppointment(id))
        );
    }

    @GetMapping("/host/{hostId}")
    public ResponseEntity<ApiResponse> getForHost(@PathVariable Long hostId) {
        return ResponseEntity.ok(
                new ApiResponse(true, "Appointments fetched",
                        appointmentService.getAppointmentsForHost(hostId))
        );
    }

    @GetMapping("/visitor/{visitorId}")
    public ResponseEntity<ApiResponse> getForVisitor(@PathVariable Long visitorId) {
        return ResponseEntity.ok(
                new ApiResponse(true, "Appointments fetched",
                        appointmentService.getAppointmentsForVisitor(visitorId))
        );
    }
}
