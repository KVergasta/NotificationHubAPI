package com.SpringNotificationHub.NotificationServ.service;

import java.util.Random;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.SpringNotificationHub.NotificationServ.model.NotificationEntity;

import lombok.Getter;
import lombok.Setter;

@Service
public class StreamService {
    
    private final KafkaTemplate<String, NotificationEntity> kafkaTemplate;

    private final Random random = new Random();

    public StreamService(KafkaTemplate<String, NotificationEntity> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @SuppressWarnings("null")
    public void sendMessage(NotificationEntity message) {
        int partition = random.nextInt(2); // Randomly select partition 0 or 1
        kafkaTemplate.send("notification-stream", partition, null, message);
    }

    @KafkaListener(topicPartitions = @TopicPartition(topic = "notification-stream", partitions = {"0", "1"}))
    public void listen(GeneratorNotification generatorNotification, NotificationEntity message) {
        generatorNotification.generatorMsg(message);
    }
}
