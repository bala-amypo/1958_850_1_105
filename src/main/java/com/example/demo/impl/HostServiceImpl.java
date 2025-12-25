package com.example.demo.service.impl;

import com.example.demo.entity.Host;
import com.example.demo.repository.HostRepository;
import com.example.demo.service.HostService;
import org.springframework.stereotype.Service;

@Service
public class HostServiceImpl implements HostService {

    private final HostRepository repository;

    public HostServiceImpl(HostRepository repository) {
        this.repository = repository;
    }

    @Override
    public Host getHost(long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Host createHost(Host host) {
        return repository.save(host);
    }
}
