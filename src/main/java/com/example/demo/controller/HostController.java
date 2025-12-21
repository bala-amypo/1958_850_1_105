package com.example.demo.controller;

import com.example.demo.dto.ApiResponse;
import com.example.demo.model.Host;
import com.example.demo.service.HostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/hosts")
public class HostController {

    private final HostService hostService;

    public HostController(HostService hostService) {
        this.hostService = hostService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse> create(@RequestBody Host host) {
        return new ResponseEntity<>(
                new ApiResponse(true, "Host created",
                        hostService.createHost(host)),
                HttpStatus.CREATED
        );
    }

    @GetMapping
    public ResponseEntity<ApiResponse> getAll() {
        return ResponseEntity.ok(
                new ApiResponse(true, "Hosts fetched",
                        hostService.getAllHosts())
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> get(@PathVariable Long id) {
        return ResponseEntity.ok(
                new ApiResponse(true, "Host fetched",
                        hostService.getHost(id))
        );
    }
}
