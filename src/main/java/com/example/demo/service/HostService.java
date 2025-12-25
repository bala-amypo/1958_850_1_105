package com.example.demo.service;

import com.example.demo.model.Host;
import java.util.List;

public interface HostService {
    Host createHost(Host host);
    Host getHostById(Long id);
    Host getHost(long id); // for test cases
    List<Host> getAllHosts();
}
