package com.example.demo.controller;
import com.example.demo.dto.AppointmentDTO;
import com.example.demo.dto.ApiResponse;
import com.example.demo.service.AppointmentService;
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

    @PostMapping
    @Operation(summary = "Create a new appointment")
    public ResponseEntity<ApiResponse> createAppointment(@Valid @RequestBody AppointmentDTO appointmentDTO) {
        AppointmentDTO createdAppointment = appointmentService.createAppointment(appointmentDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse(true, "Appointment created successfully", createdAppointment));
    }

    @GetMapping
    @Operation(summary = "Get all appointments")
    public ResponseEntity<ApiResponse> getAllAppointments() {
        List<AppointmentDTO> appointments = appointmentService.getAllAppointments();
        return ResponseEntity.ok(new ApiResponse(true, "Appointments retrieved successfully", appointments));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get appointment by ID")
    public ResponseEntity<ApiResponse> getAppointmentById(@PathVariable Long id) {
        AppointmentDTO appointment = appointmentService.getAppointmentById(id);
        return ResponseEntity.ok(new ApiResponse(true, "Appointment retrieved successfully", appointment));
    }

    @GetMapping("/host/{hostId}")
    @Operation(summary = "Get appointments by host ID")
    public ResponseEntity<ApiResponse> getAppointmentsByHostId(@PathVariable Long hostId) {
        List<AppointmentDTO> appointments = appointmentService.getAppointmentsByHostId(hostId);
        return ResponseEntity.ok(new ApiResponse(true, "Appointments retrieved successfully", appointments));
    }

    @GetMapping("/visitor/{visitorId}")
    @Operation(summary = "Get appointments by visitor ID")
    public ResponseEntity<ApiResponse> getAppointmentsByVisitorId(@PathVariable Long visitorId) {
        List<AppointmentDTO> appointments = appointmentService.getAppointmentsByVisitorId(visitorId);
        return ResponseEntity.ok(new ApiResponse(true, "Appointments retrieved successfully", appointments));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update appointment")
    public ResponseEntity<ApiResponse> updateAppointment(@PathVariable Long id, @Valid @RequestBody AppointmentDTO appointmentDTO) {
        AppointmentDTO updatedAppointment = appointmentService.updateAppointment(id, appointmentDTO);
        return ResponseEntity.ok(new ApiResponse(true, "Appointment updated successfully", updatedAppointment));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete appointment")
    public ResponseEntity<ApiResponse> deleteAppointment(@PathVariable Long id) {
        appointmentService.deleteAppointment(id);
        return ResponseEntity.ok(new ApiResponse(true, "Appointment deleted successfully", null));
    }
}
