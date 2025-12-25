package com.example.demo.service.impl;
import com.example.demo.dto.VisitorDTO;
import com.example.demo.entity.Visitor;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.VisitorRepository;
import com.example.demo.service.VisitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

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
        Visitor savedVisitor = visitorRepository.save(visitor);
        return mapToDTO(savedVisitor);
    }

    @Override
    public List<VisitorDTO> getAllVisitors() {
        return visitorRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public VisitorDTO getVisitorById(Long id) {
        Visitor visitor = visitorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Visitor not found with id: " + id));
        return mapToDTO(visitor);
    }

    @Override
    public VisitorDTO updateVisitor(Long id, VisitorDTO visitorDTO) {
        Visitor visitor = visitorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Visitor not found with id: " + id));
        visitor.setFullName(visitorDTO.getFullName());
        visitor.setEmail(visitorDTO.getEmail());
        visitor.setPhone(visitorDTO.getPhone());
        visitor.setIdProofNumber(visitorDTO.getIdProofNumber());
        Visitor updatedVisitor = visitorRepository.save(visitor);
        return mapToDTO(updatedVisitor);
    }

    @Override
    public void deleteVisitor(Long id) {
        if (!visitorRepository.existsById(id)) {
            throw new ResourceNotFoundException("Visitor not found with id: " + id);
        }
        visitorRepository.deleteById(id);
    }

    private VisitorDTO mapToDTO(Visitor visitor) {
        return new VisitorDTO(visitor.getId(), visitor.getFullName(), visitor.getEmail(),
                visitor.getPhone(), visitor.getIdProofNumber());
    }
}
