package com.example.demo.controller;
import com.example.demo.dto.VisitorDTO;
import com.example.demo.dto.ApiResponse;
import com.example.demo.service.VisitorService;
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
@RequestMapping("/api/visitors")
@Tag(name = "Visitors", description = "Visitor management APIs")
@SecurityRequirement(name = "Bearer Authentication")
public class VisitorController {
    @Autowired
    private VisitorService visitorService;

    @PostMapping
    @Operation(summary = "Create a new visitor")
    public ResponseEntity<ApiResponse> createVisitor(@Valid @RequestBody VisitorDTO visitorDTO) {
        VisitorDTO createdVisitor = visitorService.createVisitor(visitorDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse(true, "Visitor created successfully", createdVisitor));
    }

    @GetMapping
    @Operation(summary = "Get all visitors")
    public ResponseEntity<ApiResponse> getAllVisitors() {
        List<VisitorDTO> visitors = visitorService.getAllVisitors();
        return ResponseEntity.ok(new ApiResponse(true, "Visitors retrieved successfully", visitors));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get visitor by ID")
    public ResponseEntity<ApiResponse> getVisitorById(@PathVariable Long id) {
        VisitorDTO visitor = visitorService.getVisitorById(id);
        return ResponseEntity.ok(new ApiResponse(true, "Visitor retrieved successfully", visitor));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update visitor")
    public ResponseEntity<ApiResponse> updateVisitor(@PathVariable Long id, @Valid @RequestBody VisitorDTO visitorDTO) {
        VisitorDTO updatedVisitor = visitorService.updateVisitor(id, visitorDTO);
        return ResponseEntity.ok(new ApiResponse(true, "Visitor updated successfully", updatedVisitor));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete visitor")
    public ResponseEntity<ApiResponse> deleteVisitor(@PathVariable Long id) {
        visitorService.deleteVisitor(id);
        return ResponseEntity.ok(new ApiResponse(true, "Visitor deleted successfully", null));
    }
}
