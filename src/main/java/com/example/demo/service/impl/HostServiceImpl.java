package com.example.demo.service.impl;

import com.example.demo.model.Host;
import com.example.demo.repository.HostRepository;
import com.example.demo.service.HostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HostServiceImpl implements HostService {

    private HostRepository hostRepository;

    public HostServiceImpl() {}

    @Autowired
    public void setHostRepository(HostRepository hostRepository) {
        this.hostRepository = hostRepository;
    }

    @Override
    public Host createHost(Host host) {
        return hostRepository.save(host);
    }

    @Override
    public Host getHost(Long id) {
        Optional<Host> host = hostRepository.findById(id);
        return host.orElseThrow(() -> new RuntimeException("Host not found with id: " + id));
    }

    // IMPLEMENTATION FOR getAllHosts()
    @Override
    public List<Host> getAllHosts() {
        return hostRepository.findAll();
    }
}
