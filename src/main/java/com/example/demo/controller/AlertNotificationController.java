package com.example.demo.controller;
import com.example.demo.dto.AlertNotificationDTO;
import com.example.demo.dto.ApiResponse;
import com.example.demo.service.AlertNotificationService;
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
@RequestMapping("/api/alerts")
@Tag(name = "Alert Notifications", description = "Alert notification management APIs")
@SecurityRequirement(name = "Bearer Authentication")
public class AlertNotificationController {
    @Autowired
    private AlertNotificationService alertNotificationService;

    @PostMapping("/send/{visitLogId}")
    @Operation(summary = "Send alert for a visit")
    public ResponseEntity<ApiResponse> sendAlert(@PathVariable Long visitLogId, @Valid @RequestBody AlertNotificationDTO alertDTO) {
        AlertNotificationDTO sentAlert = alertNotificationService.sendAlert(visitLogId, alertDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse(true, "Alert sent successfully", sentAlert));
    }

    @GetMapping
    @Operation(summary = "Get all alerts")
    public ResponseEntity<ApiResponse> getAllAlerts() {
        List<AlertNotificationDTO> alerts = alertNotificationService.getAllAlerts();
        return ResponseEntity.ok(new ApiResponse(true, "Alerts retrieved successfully", alerts));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get alert by ID")
    public ResponseEntity<ApiResponse> getAlertById(@PathVariable Long id) {
        AlertNotificationDTO alert = alertNotificationService.getAlertById(id);
        return ResponseEntity.ok(new ApiResponse(true, "Alert retrieved successfully", alert));
    }
}
