package com.SpringNotificationHub.NotificationServ.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import lombok.Getter;
import lombok.Setter;

import com.SpringNotificationHub.NotificationServ.model.BroadcastChannel;
import com.SpringNotificationHub.NotificationServ.model.NotificationEntity;
import com.SpringNotificationHub.NotificationServ.model.ChannelType;

@Getter
@Setter
@Service
public class EmailService implements BroadcastChannel {

    @Value("${spring.mail.username}")
    private String admMailAddress;

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public String send(NotificationEntity notificationEntit) {

        try{
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setFrom(admMailAddress);
            simpleMailMessage.setTo(notificationEntit.getInfoUser());
            simpleMailMessage.setSubject(notificationEntit.getTitle());
            simpleMailMessage.setText(notificationEntit.getMessage());
            javaMailSender.send(simpleMailMessage);
            return  "The message is send";
        } catch (Exception e){
            return "The message didn't send";
        }
    }

    @Override
    public ChannelType type(){
        return ChannelType.EMAIL;
    }

}