package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name = "visitors")
public class Visitor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "Full name is required")
    private String fullName;
    
    @Pattern(regexp = "^[6-9]\\d{9}$", message = "Phone must be 10 digits starting with 6-9")
    private String phone;
    
    @NotBlank(message = "ID proof number is required")
    private String idProofNumber;
    
    private String email;
    
    // constructors
    public Visitor() {}
    
    // getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }
    
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    
    public String getIdProofNumber() { return idProofNumber; }
    public void setIdProofNumber(String idProofNumber) { this.idProofNumber = idProofNumber; }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}
