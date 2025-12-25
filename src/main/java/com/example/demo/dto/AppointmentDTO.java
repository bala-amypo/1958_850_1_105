package com.example.demo.dto;

import com.example.demo.entity.Appointment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
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
        this.appointmentDate = a.getAppointmentDate();
        this.purpose = a.getPurpose();
        this.status = a.getStatus();
    }

    // For tests: new AppointmentDTO(LocalDate ...)
    public AppointmentDTO(Long id,
                          Long visitorId,
                          Long hostId,
                          LocalDate appointmentDate,
                          String purpose,
                          String status) {
        this.id = id;
        this.visitorId = visitorId;
        this.hostId = hostId;
        this.appointmentDate = appointmentDate != null ? appointmentDate.atStartOfDay() : null;
        this.purpose = purpose;
        this.status = status;
    }
}
