package com.example.demo.repository;

import com.example.demo.entity.AlertNotification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlertNotificationRepository extends JpaRepository<AlertNotification, Long> {}
