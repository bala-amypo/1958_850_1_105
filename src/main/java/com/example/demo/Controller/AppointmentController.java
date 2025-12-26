package com.example.demo.controller;

import com.example.demo.model.Appointment;
import com.example.demo.service.AppointmentService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/appointments")
@Tag(name = "Appointments")
public class AppointmentController {

    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @PostMapping("/{visitorId}/{hostId}")
    public ResponseEntity<Appointment> create(
            @PathVariable Long visitorId,
            @PathVariable Long hostId,
            @RequestBody Appointment appointment) {
        Appointment saved = appointmentService.createAppointment(visitorId, hostId, appointment);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public Appointment getOne(@PathVariable Long id) {
        return appointmentService.getAppointment(id);
    }

    @GetMapping("/host/{hostId}")
    public List<Appointment> byHost(@PathVariable Long hostId) {
        return appointmentService.getAppointmentsForHost(hostId);
    }

    @GetMapping("/visitor/{visitorId}")
    public List<Appointment> byVisitor(@PathVariable Long visitorId) {
        return appointmentService.getAppointmentsForVisitor(visitorId);
    }
}
