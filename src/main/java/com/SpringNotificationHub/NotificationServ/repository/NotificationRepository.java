package com.SpringNotificationHub.NotificationServ.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.SpringNotificationHub.NotificationServ.model.NotificationEntity;

public interface NotificationRepository extends JpaRepository<NotificationEntity, UUID> {

    
} 
