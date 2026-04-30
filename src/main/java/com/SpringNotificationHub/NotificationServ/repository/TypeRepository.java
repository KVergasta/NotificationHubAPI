package com.SpringNotificationHub.NotificationServ.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.SpringNotificationHub.NotificationServ.model.TypeEntity;

public interface TypeRepository extends JpaRepository<TypeEntity, UUID>{
}
