package com.example.demo.service;

import com.example.demo.entity.Host;

public interface HostService {
    Host getHost(long id);
    Host createHost(Host host);
}
