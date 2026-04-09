package com.SpringNotificationHub.NotificationServ.service;

import java.nio.charset.StandardCharsets;

import javax.tools.JavaFileManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import lombok.Getter;
import lombok.Setter;

import com.SpringNotificationHub.NotificationServ.model.BroadcastChannel;
import com.SpringNotificationHub.NotificationServ.model.NotificationEntity;

import jakarta.mail.internet.MimeMessage;

import com.SpringNotificationHub.NotificationServ.model.ChannelType;

@Getter
@Setter
@Service
public class EmailService implements BroadcastChannel {

    @Value("${spring.mail.username}")
    private String admMailAddress;

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private TemplateEngine templateEngine;

    @Autowired
    private SpringTemplateEngine springTemplateEngine;

    public EmailService(JavaMailSender javaMailSender, SpringTemplateEngine springTemplateEngine){
        this.javaMailSender = javaMailSender;
        this.springTemplateEngine = springTemplateEngine;
    }

    @Override
    public String send(NotificationEntity notificationEntit) {

        try{
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            Context context = new Context();
            context.setVariable("username", notificationEntit.getInfoUser()); 
            context.setVariable("messageContent", notificationEntit.getMessage());
            context.setVariable("title", notificationEntit.getTitle()); // Use os dados da entidade!
            String process = templateEngine.process("notification.html", context);
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,
                MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                StandardCharsets.UTF_8.name()
            );
            helper.setTo(notificationEntit.getInfoUser());
            helper.setFrom(admMailAddress);
            helper.setSubject("API - Notification Hub");
            helper.setText(process,true);
            // SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            // simpleMailMessage.setFrom(admMailAddress);
            // simpleMailMessage.setTo(notificationEntit.getInfoUser());
            // simpleMailMessage.setSubject(notificationEntit.getTitle());
            // simpleMailMessage.setText(notificationEntit.getMessage());
            javaMailSender.send(mimeMessage);
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