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

    // Constructor injection (works with your existing HostServiceImpl)
    public HostController(HostService hostService) {
        this.hostService = hostService;
    }

    // Create host
    @PostMapping
    public ResponseEntity<Host> createHost(@RequestBody Host host) {
        Host saved = hostService.createHost(host);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    // Get one host by id
    @GetMapping("/{id}")
    public Host getHost(@PathVariable Long id) {
        return hostService.getHost(id);
    }

    // List all hosts
    @GetMapping
    public List<Host> getAllHosts() {
        return hostService.getAllHosts();
    }
}
