package com.SpringNotificationHub.NotificationServ.service;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.SpringNotificationHub.NotificationServ.exceptions.NotFoundException;
import com.SpringNotificationHub.NotificationServ.model.BroadcastChannel;
import com.SpringNotificationHub.NotificationServ.model.NotificationEntity;
import com.SpringNotificationHub.NotificationServ.model.StatusType;

import lombok.Getter;
import lombok.Setter;

@Service
public class StreamService {
    
    private final KafkaTemplate<String, NotificationEntity> kafkaTemplate;

    private final Random random = new Random();

    @Autowired
    private GeneratorNotification generatorNotification;

    public StreamService(KafkaTemplate<String, NotificationEntity> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @SuppressWarnings("null")
    public void sendMessage(NotificationEntity message) {
        int partition = random.nextInt(2); // Randomly select partition 0 or 1
        kafkaTemplate.send("notification-stream", partition, null, message);
    }

@KafkaListener(topicPartitions = @TopicPartition(topic = "notification-stream", partitions = {"0", "1"}))
public void listen(NotificationEntity message) { // Remova o Generator do parâmetro
    try {
        generatorNotification.generatorMsg(message); 
        message.setStatus(StatusType.SENT);
        generatorNotification.saveNotification(message); 
    } catch (Exception e) {
        message.setStatus(StatusType.FAILED);
        generatorNotification.saveNotification(message); 
    }
}
}
