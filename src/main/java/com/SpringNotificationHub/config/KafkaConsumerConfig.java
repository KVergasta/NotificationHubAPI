package com.SpringNotificationHub.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

import com.SpringNotificationHub.NotificationServ.model.NotificationEntity;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.kafka.support.serializer.JsonDeserializer;

@EnableKafka
@Configuration
public class KafkaConsumerConfig {

      @Value("${spring.kafka.bootstrap-servers}")
      private String bootstrapAddress;
      @Value("${spring.kafka.consumer.group-id}")
      private String groupId;

 @Bean
public ConsumerFactory<String, NotificationEntity> consumerFactory() {
    Map<String, Object> props = new HashMap<>();
    props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
    props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
    props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
    props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
    props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
    
    // ESTA É A PARTE CRUCIAL:
    JsonDeserializer<NotificationEntity> jsonDeserializer = new JsonDeserializer<>(NotificationEntity.class);
    jsonDeserializer.addTrustedPackages("com.SpringNotificationHub.NotificationServ.model");
    jsonDeserializer.setUseTypeHeaders(false); // Evita conflitos de pacotes se o Producer for de outro projeto

    return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(), jsonDeserializer);
}

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, NotificationEntity> 
      kafkaListenerContainerFactory() {
  
        ConcurrentKafkaListenerContainerFactory<String, NotificationEntity> factory =
          new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }
}