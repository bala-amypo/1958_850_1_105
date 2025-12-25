package com.example.demo.controller;

import com.example.demo.model.Appointment;
import com.example.demo.service.AppointmentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {

    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @PostMapping
    public Appointment createAppointment(@RequestBody Appointment appointment) {
        return appointmentService.createAppointment(appointment);
    }

    @GetMapping("/{id}")
    public Appointment getAppointment(@PathVariable Long id) {
        return appointmentService.getAppointmentById(id);
    }

    @GetMapping("/host/{hostId}")
    public List<Appointment> getByHost(@PathVariable Long hostId) {
        return appointmentService.getAppointmentsByHost(hostId);
    }

    @GetMapping("/visitor/{visitorId}")
    public List<Appointment> getByVisitor(@PathVariable Long visitorId) {
        return appointmentService.getAppointmentsByVisitor(visitorId);
    }
}
