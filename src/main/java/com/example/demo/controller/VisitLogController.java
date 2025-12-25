package com.example.demo.controller;

import com.example.demo.dto.VisitLogDTO;
import com.example.demo.entity.VisitLog;
import com.example.demo.service.VisitLogService;
import com.example.demo.util.DTOConverter;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/visitlogs")
@CrossOrigin(origins = "*")
public class VisitLogController {
    
    @Autowired
    private VisitLogService visitLogService;

    @PostMapping("/checkin/{visitorId}/{hostId}")
    public ResponseEntity<ApiResponse> checkInVisitor(
            @PathVariable Long visitorId, 
            @PathVariable Long hostId, 
            @Valid @RequestBody VisitLogDTO visitLogDTO) {
        VisitLog visitLog = visitLogService.checkInVisitor(visitorId, hostId, visitLogDTO.getPurpose());
        return ResponseEntity.ok(new ApiResponse(true, "Check-in successful", DTOConverter.toVisitLogDTO(visitLog)));
    }

    @PutMapping("/checkout/{visitLogId}")
    public ResponseEntity<ApiResponse> checkOutVisitor(@PathVariable Long visitLogId) {
        VisitLog visitLog = visitLogService.checkOutVisitor(visitLogId);
        return ResponseEntity.ok(new ApiResponse(true, "Check-out successful", DTOConverter.toVisitLogDTO(visitLog)));
    }

    @GetMapping
    public ResponseEntity<ApiResponse> getAllVisitLogs() {
        List<VisitLogDTO> visitLogs = DTOConverter.toVisitLogDTOList(visitLogService.getAllVisitLogs());
        return ResponseEntity.ok(new ApiResponse(true, "Visit logs retrieved", visitLogs));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getVisitLogById(@PathVariable Long id) {
        VisitLog visitLog = visitLogService.getVisitLogById(id);
        return ResponseEntity.ok(new ApiResponse(true, "Visit log found", DTOConverter.toVisitLogDTO(visitLog)));
    }

    @GetMapping("/active")
    public ResponseEntity<ApiResponse> getActiveVisits() {
        List<VisitLogDTO> activeVisits = DTOConverter.toVisitLogDTOList(visitLogService.getActiveVisits());
        return ResponseEntity.ok(new ApiResponse(true, "Active visits retrieved", activeVisits));
    }

    @GetMapping("/host/{hostId}")
    public ResponseEntity<ApiResponse> getVisitLogsByHostId(@PathVariable Long hostId) {
        List<VisitLogDTO> visitLogs = DTOConverter.toVisitLogDTOList(visitLogService.getVisitLogsByHostId(hostId));
        return ResponseEntity.ok(new ApiResponse(true, "Host visit logs retrieved", visitLogs));
    }

    @GetMapping("/visitor/{visitorId}")
    public ResponseEntity<ApiResponse> getVisitLogsByVisitorId(@PathVariable Long visitorId) {
        List<VisitLogDTO> visitLogs = DTOConverter.toVisitLogDTOList(visitLogService.getVisitLogsByVisitorId(visitorId));
        return ResponseEntity.ok(new ApiResponse(true, "Visitor visit logs retrieved", visitLogs));
    }
}
