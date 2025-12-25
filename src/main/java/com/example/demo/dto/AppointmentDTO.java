package com.example.demo.dto;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentDTO {
    private Long id;

    private Long visitorId;

    private Long hostId;

    private LocalDate appointmentDate;

    @NotBlank(message = "Purpose is required")
    private String purpose;

    private String status;
}
