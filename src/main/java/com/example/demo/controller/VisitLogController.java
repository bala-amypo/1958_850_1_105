package com.example.demo.controller;

import com.example.demo.dto.ApiResponse;
import com.example.demo.dto.VisitLogDTO;
import com.example.demo.entity.VisitLog;
import com.example.demo.service.VisitLogService;
import com.example.demo.util.DTOConverter;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/visitlogs")
@Tag(name = "VisitLogs", description = "Visit log management APIs")
@SecurityRequirement(name = "Bearer Authentication")
public class VisitLogController {

    @Autowired
    private VisitLogService visitLogService;

    @PostMapping("/checkin/{visitorId}/{hostId}")
    @Operation(summary = "Check in a visitor")
    public ResponseEntity<ApiResponse> checkInVisitor(
            @PathVariable Long visitorId,
            @PathVariable Long hostId,
            @Valid @RequestBody VisitLogDTO visitLogDTO) {

        VisitLog visitLog = visitLogService.checkInVisitor(
                visitorId,
                hostId,
                visitLogDTO.getPurpose()
        );
        VisitLogDTO body = DTOConverter.toVisitLogDTO(visitLog);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse(true, "Visitor checked in successfully", body));
    }

    @PutMapping("/checkout/{visitLogId}")
    @Operation(summary = "Check out a visitor")
    public ResponseEntity<ApiResponse> checkOutVisitor(@PathVariable Long visitLogId) {
        VisitLog visitLog = visitLogService.checkOutVisitor(visitLogId);
        VisitLogDTO body = DTOConverter.toVisitLogDTO(visitLog);
        return ResponseEntity.ok(new ApiResponse(true, "Visitor checked out successfully", body));
    }

    @GetMapping
    @Operation(summary = "Get all visit logs")
    public ResponseEntity<ApiResponse> getAllVisitLogs() {
        List<VisitLog> logs = visitLogService.getAllVisitLogs();
        List<VisitLogDTO> body = DTOConverter.toVisitLogDTOList(logs);
        return ResponseEntity.ok(new ApiResponse(true, "Visit logs retrieved successfully", body));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get visit log by ID")
    public ResponseEntity<ApiResponse> getVisitLogById(@PathVariable Long id) {
        VisitLog log = visitLogService.getVisitLogById(id);
        VisitLogDTO body = DTOConverter.toVisitLogDTO(log);
        return ResponseEntity.ok(new ApiResponse(true, "Visit log retrieved successfully", body));
    }

    @GetMapping("/active")
    @Operation(summary = "Get all active visits")
    public ResponseEntity<ApiResponse> getActiveVisits() {
        List<VisitLog> logs = visitLogService.getActiveVisits();
        List<VisitLogDTO> body = DTOConverter.toVisitLogDTOList(logs);
        return ResponseEntity.ok(new ApiResponse(true, "Active visits retrieved successfully", body));
    }

    @GetMapping("/host/{hostId}")
    @Operation(summary = "Get visit logs by host ID")
    public ResponseEntity<ApiResponse> getVisitLogsByHostId(@PathVariable Long hostId) {
        List<VisitLog> logs = visitLogService.getVisitLogsByHostId(hostId);
        List<VisitLogDTO> body = DTOConverter.toVisitLogDTOList(logs);
        return ResponseEntity.ok(new ApiResponse(true, "Host visit logs retrieved successfully", body));
    }

    @GetMapping("/visitor/{visitorId}")
    @Operation(summary = "Get visit logs by visitor ID")
    public ResponseEntity<ApiResponse> getVisitLogsByVisitorId(@PathVariable Long visitorId) {
        List<VisitLog> logs = visitLogService.getVisitLogsByVisitorId(visitorId);
        List<VisitLogDTO> body = DTOConverter.toVisitLogDTOList(logs);
        return ResponseEntity.ok(new ApiResponse(true, "Visitor visit logs retrieved successfully", body));
    }
}
