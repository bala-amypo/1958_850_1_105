package com.example.demo.dto;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VisitLogDTO {
    private Long id;

    private Long visitorId;

    private Long hostId;

    private LocalDateTime checkInTime;

    private LocalDateTime checkOutTime;

    @NotBlank(message = "Purpose is required")
    private String purpose;

    private Boolean accessGranted;

    private Boolean alertSent;
}
