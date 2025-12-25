package com.example.demo.controller;

import com.example.demo.entity.Visitor;
import com.example.demo.service.VisitorService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/visitors")
public class VisitorController {

    private final VisitorService service;

    public VisitorController(VisitorService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public Visitor getVisitor(@PathVariable long id) {
        return service.getVisitor(id);
    }

    @PostMapping
    public Visitor createVisitor(@RequestBody Visitor visitor) {
        return service.createVisitor(visitor);
    }
}
