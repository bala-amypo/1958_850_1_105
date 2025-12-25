package com.example.demo.dto;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AlertNotificationDTO {
    private Long id;

    private Long visitLogId;

    @NotBlank(message = "Sent to is required")
    private String sentTo;

    @NotBlank(message = "Alert message is required")
    private String alertMessage;

    private LocalDateTime sentAt;
}
