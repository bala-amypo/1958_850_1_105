package com.example.demo.service;
import com.example.demo.dto.HostDTO;
import java.util.List;

public interface HostService {
    HostDTO createHost(HostDTO hostDTO);
    List<HostDTO> getAllHosts();
    HostDTO getHostById(Long id);
    HostDTO updateHost(Long id, HostDTO hostDTO);
    void deleteHost(Long id);
}
