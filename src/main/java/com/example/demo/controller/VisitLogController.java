package com.example.demo.controller;
import com.example.demo.dto.VisitLogDTO;
import com.example.demo.dto.ApiResponse;
import com.example.demo.service.VisitLogService;
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
@RequestMapping("/api/visits")
@Tag(name = "Visit Logs", description = "Visitor check-in/out and tracking APIs")
@SecurityRequirement(name = "Bearer Authentication")
public class VisitLogController {
    @Autowired
    private VisitLogService visitLogService;

    @PostMapping("/check-in")
    @Operation(summary = "Check-in visitor")
    public ResponseEntity<ApiResponse> checkInVisitor(@Valid @RequestBody VisitLogDTO visitLogDTO) {
        VisitLogDTO checkedInVisit = visitLogService.checkInVisitor(visitLogDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse(true, "Visitor checked in successfully", checkedInVisit));
    }

    @PostMapping("/check-out/{visitLogId}")
    @Operation(summary = "Check-out visitor")
    public ResponseEntity<ApiResponse> checkOutVisitor(@PathVariable Long visitLogId) {
        VisitLogDTO checkedOutVisit = visitLogService.checkOutVisitor(visitLogId);
        return ResponseEntity.ok(new ApiResponse(true, "Visitor checked out successfully", checkedOutVisit));
    }

    @GetMapping
    @Operation(summary = "Get all visit logs")
    public ResponseEntity<ApiResponse> getAllVisitLogs() {
        List<VisitLogDTO> visitLogs = visitLogService.getAllVisitLogs();
        return ResponseEntity.ok(new ApiResponse(true, "Visit logs retrieved successfully", visitLogs));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get visit log by ID")
    public ResponseEntity<ApiResponse> getVisitLogById(@PathVariable Long id) {
        VisitLogDTO visitLog = visitLogService.getVisitLogById(id);
        return ResponseEntity.ok(new ApiResponse(true, "Visit log retrieved successfully", visitLog));
    }

    @GetMapping("/active")
    @Operation(summary = "Get active visits")
    public ResponseEntity<ApiResponse> getActiveVisits() {
        List<VisitLogDTO> activeVisits = visitLogService.getActiveVisits();
        return ResponseEntity.ok(new ApiResponse(true, "Active visits retrieved successfully", activeVisits));
    }

    @GetMapping("/host/{hostId}")
    @Operation(summary = "Get visit logs by host ID")
    public ResponseEntity<ApiResponse> getVisitLogsByHostId(@PathVariable Long hostId) {
        List<VisitLogDTO> visitLogs = visitLogService.getVisitLogsByHostId(hostId);
        return ResponseEntity.ok(new ApiResponse(true, "Visit logs retrieved successfully", visitLogs));
    }

    @GetMapping("/visitor/{visitorId}")
    @Operation(summary = "Get visit logs by visitor ID")
    public ResponseEntity<ApiResponse> getVisitLogsByVisitorId(@PathVariable Long visitorId) {
        List<VisitLogDTO> visitLogs = visitLogService.getVisitLogsByVisitorId(visitorId);
        return ResponseEntity.ok(new ApiResponse(true, "Visit logs retrieved successfully", visitLogs));
    }
}
