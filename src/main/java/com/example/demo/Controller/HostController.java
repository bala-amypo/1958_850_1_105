package com.example.demo.controller;

import com.example.demo.model.Host;
import com.example.demo.service.HostService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hosts")
@Tag(name = "Hosts")
public class HostController {

    private final HostService hostService;

    public HostController(HostService hostService) {
        this.hostService = hostService;
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Host host) {
        try {
            Host saved = hostService.createHost(host);
            return new ResponseEntity<>(saved, HttpStatus.CREATED);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Could not create host");
        }
    }

    @GetMapping("/{id}")
    public Host getOne(@PathVariable Long id) {
        return hostService.getHost(id);
    }

    @GetMapping
    public List<Host> getAll() {
        return hostService.getAllHosts();
    }
}
