package com.example.demo.service.impl;

import com.example.demo.model.Host;
import com.example.demo.repository.HostRepository;
import com.example.demo.service.HostService;
import com.example.demo.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class HostServiceImpl implements HostService {

    private final HostRepository hostRepository;

    public HostServiceImpl(HostRepository hostRepository) { this.hostRepository = hostRepository; }
    public HostServiceImpl() { this.hostRepository = null; } // no-arg constructor for tests

    @Override
    public Host createHost(Host host) { return hostRepository.save(host); }

    @Override
    public Host getHostById(Long id) {
        return hostRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Host not found"));
    }

    @Override
    public Host getHost(long id) {
        return hostRepository.findById(id).orElse(null);
    }

    @Override
    public List<Host> getAllHosts() { return hostRepository.findAll(); }
}
