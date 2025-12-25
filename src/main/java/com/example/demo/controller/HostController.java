package com.example.demo.controller;

import com.example.demo.entity.Host;
import com.example.demo.service.HostService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hosts")
public class HostController {

    private final HostService service;

    public HostController(HostService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public Host getHost(@PathVariable long id) {
        return service.getHost(id);
    }

    @PostMapping
    public Host createHost(@RequestBody Host host) {
        return service.createHost(host);
    }
}
