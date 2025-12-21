package com.example.demo.controller;

import com.example.demo.dto.ApiResponse;
import com.example.demo.model.Visitor;
import com.example.demo.service.VisitorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/visitors")
public class VisitorController {

    private final VisitorService visitorService;

    public VisitorController(VisitorService visitorService) {
        this.visitorService = visitorService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse> create(@RequestBody Visitor visitor) {
        return new ResponseEntity<>(
                new ApiResponse(true, "Visitor created",
                        visitorService.createVisitor(visitor)),
                HttpStatus.CREATED
        );
    }

    @GetMapping
    public ResponseEntity<ApiResponse> getAll() {
        return ResponseEntity.ok(
                new ApiResponse(true, "Visitors fetched",
                        visitorService.getAllVisitors())
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> get(@PathVariable Long id) {
        return ResponseEntity.ok(
                new ApiResponse(true, "Visitor fetched",
                        visitorService.getVisitor(id))
        );
    }
}
