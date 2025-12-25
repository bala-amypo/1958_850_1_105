package com.example.demo.service.impl;

import com.example.demo.model.Visitor;
import com.example.demo.repository.VisitorRepository;
import com.example.demo.service.VisitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class VisitorServiceImpl implements VisitorService {
    
    @Autowired
    private VisitorRepository visitorRepository;
    
    public VisitorServiceImpl() {}
    
    public VisitorServiceImpl(VisitorRepository visitorRepository) {
        this.visitorRepository = visitorRepository;
    }
    
    @Override
    public Visitor createVisitor(Visitor visitor) {
        return visitorRepository.save(visitor);
    }
    
    @Override
    public Visitor getVisitor(Long id) {
        Optional<Visitor> visitor = visitorRepository.findById(id);
        return visitor.orElseThrow(() -> new RuntimeException("Visitor not found with id: " + id));
    }
    
    @Override
    public List<Visitor> getAllVisitors() {
        return visitorRepository.findAll();
    }
}
