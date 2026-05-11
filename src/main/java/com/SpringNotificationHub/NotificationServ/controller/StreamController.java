package com.SpringNotificationHub.NotificationServ.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.SpringNotificationHub.NotificationServ.model.BroadcastChannel;
import com.SpringNotificationHub.NotificationServ.model.NotificationEntity;
import com.SpringNotificationHub.NotificationServ.service.GeneratorNotification;
import com.SpringNotificationHub.NotificationServ.service.StreamService;

@RestController
@RequestMapping("/stream")
public class StreamController {
    
    public final StreamService streamService;
    
    @Autowired
    private GeneratorNotification generatorNotification;

    public StreamController(StreamService streamService) {
        this.streamService = streamService;
    }

    @PostMapping("/kafka")
    public ResponseEntity<?> sendMessage(@RequestBody NotificationEntity message) {
        streamService.listen(generatorNotification,message);
            return ResponseEntity
            .status(HttpStatus.ACCEPTED)
            .header("title", message.getStatus().name())
            .body(null);
        }

}
