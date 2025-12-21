package com.example.demo.controller;

import com.example.demo.dto.ApiResponse;
import com.example.demo.service.VisitLogService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/visits")
public class VisitLogController {

    private final VisitLogService visitLogService;

    public VisitLogController(VisitLogService visitLogService) {
        this.visitLogService = visitLogService;
    }

    @PostMapping("/checkin/{visitorId}/{hostId}")
    public ResponseEntity<ApiResponse> checkIn(@PathVariable Long visitorId,
                                               @PathVariable Long hostId,
                                               @RequestBody String purpose) {

        return new ResponseEntity<>(
                new ApiResponse(true, "Checked in",
                        visitLogService.checkInVisitor(visitorId, hostId, purpose)),
                HttpStatus.CREATED
        );
    }

    @PostMapping("/checkout/{visitLogId}")
    public ResponseEntity<ApiResponse> checkOut(@PathVariable Long visitLogId) {
        return ResponseEntity.ok(
                new ApiResponse(true, "Checked out",
                        visitLogService.checkOutVisitor(visitLogId))
        );
    }

    @GetMapping("/active")
    public ResponseEntity<ApiResponse> getActive() {
        return ResponseEntity.ok(
                new ApiResponse(true, "Active visits fetched",
                        visitLogService.getActiveVisits())
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> get(@PathVariable Long id) {
        return ResponseEntity.ok(
                new ApiResponse(true, "Visit fetched",
                        visitLogService.getVisitLog(id))
        );
    }
}
