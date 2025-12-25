package com.example.demo.dto;

import com.example.demo.entity.Host;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HostDTO {
    private Long id;
    private String hostName;
    private String fullname;
    private String email;
    private String department;
    private String phone;

    // Helper constructor for tests
    public HostDTO(Host host) {
        this.id = host.getId();
        this.hostName = host.getHostName();
        this.fullname = host.getFullname();
        this.email = host.getEmail();
        this.department = host.getDepartment();
        this.phone = host.getPhone();
    }
}
