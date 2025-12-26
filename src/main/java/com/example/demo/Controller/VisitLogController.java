package com.example.demo.controller;

import com.example.demo.model.VisitLog;
import com.example.demo.service.VisitLogService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/visits")
@Tag(name = "Visits")
public class VisitLogController {

    private final VisitLogService visitLogService;

    public VisitLogController(VisitLogService visitLogService) {
        this.visitLogService = visitLogService;
    }

    @PostMapping("/checkin/{visitorId}/{hostId}")
    public ResponseEntity<VisitLog> checkIn(
            @PathVariable Long visitorId,
            @PathVariable Long hostId,
            @RequestParam(required = false) String purpose) {
        return ResponseEntity.ok(
                visitLogService.checkInVisitor(visitorId, hostId, purpose)
        );
    }

    @PostMapping("/checkout/{visitLogId}")
    public ResponseEntity<VisitLog> checkOut(@PathVariable Long visitLogId) {
        return ResponseEntity.ok(visitLogService.checkOutVisitor(visitLogId));
    }

    @GetMapping("/active")
    public List<VisitLog> getActive() {
        return visitLogService.getActiveVisits();
    }

    @GetMapping("/{id}")
    public VisitLog getOne(@PathVariable Long id) {
        return visitLogService.getVisitLog(id);
    }
}
