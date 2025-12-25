package com.example.demo.controller;
import com.example.demo.dto.HostDTO;
import com.example.demo.dto.ApiResponse;
import com.example.demo.service.HostService;
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
@RequestMapping("/api/hosts")
@Tag(name = "Hosts", description = "Host/Employee management APIs")
@SecurityRequirement(name = "Bearer Authentication")
public class HostController {
    @Autowired
    private HostService hostService;

    @PostMapping
    @Operation(summary = "Create a new host")
    public ResponseEntity<ApiResponse> createHost(@Valid @RequestBody HostDTO hostDTO) {
        HostDTO createdHost = hostService.createHost(hostDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse(true, "Host created successfully", createdHost));
    }

    @GetMapping
    @Operation(summary = "Get all hosts")
    public ResponseEntity<ApiResponse> getAllHosts() {
        List<HostDTO> hosts = hostService.getAllHosts();
        return ResponseEntity.ok(new ApiResponse(true, "Hosts retrieved successfully", hosts));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get host by ID")
    public ResponseEntity<ApiResponse> getHostById(@PathVariable Long id) {
        HostDTO host = hostService.getHostById(id);
        return ResponseEntity.ok(new ApiResponse(true, "Host retrieved successfully", host));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update host")
    public ResponseEntity<ApiResponse> updateHost(@PathVariable Long id, @Valid @RequestBody HostDTO hostDTO) {
        HostDTO updatedHost = hostService.updateHost(id, hostDTO);
        return ResponseEntity.ok(new ApiResponse(true, "Host updated successfully", updatedHost));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete host")
    public ResponseEntity<ApiResponse> deleteHost(@PathVariable Long id) {
        hostService.deleteHost(id);
        return ResponseEntity.ok(new ApiResponse(true, "Host deleted successfully", null));
    }
}
