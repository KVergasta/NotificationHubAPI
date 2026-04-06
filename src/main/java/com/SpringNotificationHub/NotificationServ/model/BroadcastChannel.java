package com.SpringNotificationHub.NotificationServ.model;

public interface BroadcastChannel {
        public String send(NotificationEntity notificationEntity);
        public ChannelType type();
}