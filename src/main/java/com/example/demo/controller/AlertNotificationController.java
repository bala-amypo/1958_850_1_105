package com.example.demo.controller;

import com.example.demo.entity.AlertNotification;
import com.example.demo.service.AlertNotificationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alerts")
public class AlertNotificationController {

    private final AlertNotificationService service;

    public AlertNotificationController(AlertNotificationService service) {
        this.service = service;
    }

    @GetMapping
    public List<AlertNotification> getAllAlerts() {
        return service.getAllAlerts();
    }

    @PostMapping
    public AlertNotification createAlert(@RequestBody AlertNotification alert) {
        return service.createAlert(alert);
    }
}
