package com.example.demo.controller;

import com.example.demo.model.VisitLog;
import com.example.demo.service.VisitLogService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/visits")
public class VisitLogController {

    private final VisitLogService visitLogService;

    public VisitLogController(VisitLogService visitLogService) {
        this.visitLogService = visitLogService;
    }

    @PostMapping("/check-in")
    public VisitLog checkIn(@RequestParam Long visitorId,
                            @RequestParam Long hostId,
                            @RequestParam String purpose) {
        return visitLogService.checkInVisitor(visitorId, hostId, purpose);
    }

    @PostMapping("/check-out/{id}")
    public VisitLog checkOut(@PathVariable Long id) {
        return visitLogService.checkOutVisitor(id);
    }

    @GetMapping("/{id}")
    public VisitLog getVisit(@PathVariable Long id) {
        return visitLogService.getVisitLog(id);
    }

    @GetMapping("/active")
    public List<VisitLog> getActiveVisits() {
        return visitLogService.getActiveVisits();
    }
}
