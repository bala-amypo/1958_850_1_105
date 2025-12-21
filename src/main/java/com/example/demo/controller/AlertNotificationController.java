package com.example.demo.controller;

import com.example.demo.entity.AlertNotification;
import com.example.demo.service.AlertNotificationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/alerts")
@Tag(name = "Alert Notifications", description = "Alert notification management APIs")
public class AlertNotificationController {

    private final AlertNotificationService alertNotificationService;

    public AlertNotificationController(AlertNotificationService alertNotificationService) {
        this.alertNotificationService = alertNotificationService;
    }

    /**
     * Send alert for a visit log
     */
    @PostMapping("/send/{visitLogId}")
    public AlertNotification sendAlert(@PathVariable Long visitLogId) {
        return alertNotificationService.sendAlert(visitLogId);
    }

    /**
     * Get alert by ID
     */
    @GetMapping("/{id}")
    public AlertNotification getAlert(@PathVariable Long id) {
        return alertNotificationService.getAlert(id);
    }

    /**
     * Get all alerts
     */
    @GetMapping
    public List<AlertNotification> getAllAlerts() {
        return alertNotificationService.getAllAlerts();
    }
}
