package com.SpringNotificationHub.NotificationServ.repository;

import java.nio.channels.Channel;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.SpringNotificationHub.NotificationServ.model.ChannelType;
import com.SpringNotificationHub.NotificationServ.model.TypeEntity;

public interface TypeRepository extends JpaRepository<TypeEntity, UUID>{
}
