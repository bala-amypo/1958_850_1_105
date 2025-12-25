package com.example.demo.repository;

import com.example.demo.model.AlertNotification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlertNotificationRepository extends JpaRepository<AlertNotification, Long> {}
