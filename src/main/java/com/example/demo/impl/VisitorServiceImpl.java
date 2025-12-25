package com.example.demo.service.impl;

import com.example.demo.dto.VisitorDTO;
import com.example.demo.entity.Visitor;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.VisitorRepository;
import com.example.demo.service.VisitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VisitorServiceImpl implements VisitorService {

    @Autowired
    private VisitorRepository visitorRepository;

    @Override
    public VisitorDTO createVisitor(VisitorDTO visitorDTO) {
        Visitor visitor = new Visitor();
        visitor.setFullName(visitorDTO.getFullName());
        visitor.setEmail(visitorDTO.getEmail());
        visitor.setPhone(visitorDTO.getPhone());
        visitor.setIdProofNumber(visitorDTO.getIdProofNumber());
        Visitor saved = visitorRepository.save(visitor);
        visitorDTO.setId(saved.getId());
        return visitorDTO;
    }

    @Override
    public List<VisitorDTO> getAllVisitors() {
        return visitorRepository.findAll().stream().map(v -> {
            VisitorDTO dto = new VisitorDTO();
            dto.setId(v.getId());
            dto.setFullName(v.getFullName());
            dto.setEmail(v.getEmail());
            dto.setPhone(v.getPhone());
            dto.setIdProofNumber(v.getIdProofNumber());
            return dto;
        }).toList();
    }

    @Override
    public VisitorDTO getVisitorById(Long id) {
        Visitor v = visitorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Visitor not found"));
        VisitorDTO dto = new VisitorDTO();
        dto.setId(v.getId());
        dto.setFullName(v.getFullName());
        dto.setEmail(v.getEmail());
        dto.setPhone(v.getPhone());
        dto.setIdProofNumber(v.getIdProofNumber());
        return dto;
    }

    @Override
    public VisitorDTO updateVisitor(Long id, VisitorDTO visitorDTO) {
        Visitor v = visitorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Visitor not found"));
        v.setFullName(visitorDTO.getFullName());
        v.setEmail(visitorDTO.getEmail());
        v.setPhone(visitorDTO.getPhone());
        v.setIdProofNumber(visitorDTO.getIdProofNumber());
        visitorRepository.save(v);
        visitorDTO.setId(v.getId());
        return visitorDTO;
    }

    @Override
    public void deleteVisitor(Long id) {
        if (!visitorRepository.existsById(id)) {
            throw new ResourceNotFoundException("Visitor not found");
        }
        visitorRepository.deleteById(id);
    }

    // Helper used by tests to get entity directly
    public Visitor getVisitorEntity(Long id) {
        return visitorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Visitor not found"));
    }
}
