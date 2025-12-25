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

    public VisitorServiceImpl() {
    }

    public VisitorServiceImpl(VisitorRepository visitorRepository) {
        this.visitorRepository = visitorRepository;
    }

    @Override
    public VisitorDTO createVisitor(VisitorDTO visitorDTO) {
        Visitor visitor = fromDto(visitorDTO);
        Visitor saved = visitorRepository.save(visitor);
        return toDto(saved);
    }

    @Override
    public List<VisitorDTO> getAllVisitors() {
        return visitorRepository.findAll().stream()
                .map(VisitorServiceImpl::toDto)
                .toList();
    }

    @Override
    public VisitorDTO getVisitorById(Long id) {
        Visitor v = visitorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Visitor not found"));
        return toDto(v);
    }

    @Override
    public VisitorDTO updateVisitor(Long id, VisitorDTO visitorDTO) {
        Visitor v = visitorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Visitor not found"));
        v.setFullName(visitorDTO.getFullName());
        v.setEmail(visitorDTO.getEmail());
        v.setPhone(visitorDTO.getPhone());
        v.setIdProofNumber(visitorDTO.getIdProofNumber());
        Visitor saved = visitorRepository.save(v);
        return toDto(saved);
    }

    @Override
    public void deleteVisitor(Long id) {
        if (!visitorRepository.existsById(id)) {
            throw new ResourceNotFoundException("Visitor not found");
        }
        visitorRepository.deleteById(id);
    }

    // Helper used by tests: visitorService.getVisitor(long)
    public Visitor getVisitor(long id) {
        return visitorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Visitor not found"));
    }

    // Static helpers so tests can easily convert if they want

    public static VisitorDTO toDto(Visitor v) {
        VisitorDTO dto = new VisitorDTO();
        dto.setId(v.getId());
        dto.setFullName(v.getFullName());
        dto.setEmail(v.getEmail());
        dto.setPhone(v.getPhone());
        dto.setIdProofNumber(v.getIdProofNumber());
        return dto;
    }

    public static Visitor fromDto(VisitorDTO dto) {
        Visitor v = new Visitor();
        v.setId(dto.getId());
        v.setFullName(dto.getFullName());
        v.setEmail(dto.getEmail());
        v.setPhone(dto.getPhone());
        v.setIdProofNumber(dto.getIdProofNumber());
        return v;
    }
}
