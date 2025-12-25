package com.example.demo.dto;

import com.example.demo.entity.Appointment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentDTO {

    private Long id;
    private Long visitorId;
    private Long hostId;
    private LocalDateTime appointmentDate;
    private String purpose;
    private String status;

    public AppointmentDTO(Appointment a) {
        this.id = a.getId();
        this.visitorId = a.getVisitor() != null ? a.getVisitor().getId() : null;
        this.hostId = a.getHost() != null ? a.getHost().getId() : null;

        // ✅ FIX HERE
        this.appointmentDate = a.getAppointmentDate() != null
                ? a.getAppointmentDate().atStartOfDay()
                : null;

        this.purpose = a.getPurpose();
        this.status = a.getStatus();
    }
}
