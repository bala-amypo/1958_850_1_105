package com.example.demo.controller;

import com.example.demo.dto.ApiResponse;
import com.example.demo.service.AlertNotificationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/alerts")
public class AlertNotificationController {

    private final AlertNotificationService alertService;

    public AlertNotificationController(AlertNotificationService alertService) {
        this.alertService = alertService;
    }

    @PostMapping("/send/{visitLogId}")
    public ResponseEntity<ApiResponse> send(@PathVariable Long visitLogId) {
        return new ResponseEntity<>(
                new ApiResponse(true, "Alert sent",
                        alertService.sendAlert(visitLogId)),
                HttpStatus.CREATED
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> get(@PathVariable Long id) {
        return ResponseEntity.ok(
                new ApiResponse(true, "Alert fetched",
                        alertService.getAlert(id))
        );
    }

    @GetMapping
    public ResponseEntity<ApiResponse> getAll() {
        return ResponseEntity.ok(
                new ApiResponse(true, "Alerts fetched",
                        alertService.getAllAlerts())
        );
    }
}
