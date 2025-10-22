package com.kanbanflow.kanban_api.service.impl;

import com.kanbanflow.kanban_api.dto.ProjectCreatedEventDto;
import com.kanbanflow.kanban_api.dto.UserAddedEventDto;
import com.kanbanflow.kanban_api.dto.UserRegistrationRequestDto;
import com.kanbanflow.kanban_api.service.NotificationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class NotificationServiceImpl implements NotificationService {

    private static final String fromMailId = "rakeshjakka9@gmail.com";
    private static final Logger LOGGER = LoggerFactory.getLogger(NotificationServiceImpl.class);

    private JavaMailSender javaMailSender;

    @Autowired
    public NotificationServiceImpl(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

//    @Override
//    @Async
//    public void sendWelcomeEmail(UserRegistrationRequestDto requestDto) {
//        try {
//            SimpleMailMessage mailMessage = new SimpleMailMessage();
//            LOGGER.info("Starting to send welcome email to {} on thread: {}", requestDto.getUsername(), Thread.currentThread().getName());
//            mailMessage.setFrom(fromMailId);
//            mailMessage.setTo(requestDto.getEmail());
//            mailMessage.setSubject("Test Email from Spring Boot");
//            mailMessage.setText("Hello, "+requestDto.getUsername()+". You are registered to the platform successfully.");
//            javaMailSender.send(mailMessage);
//            LOGGER.info("Successfully sent welcome email to {} on thread: {}", requestDto.getUsername(), Thread.currentThread().getName());
//        } catch (Exception e){
//            LOGGER.info("An exception occured {}",e.getMessage());
//        }
//    }

    @Override
    @KafkaListener(topics = "user_created", groupId = "kanban-group")
    public void sendWelcomeEmail(UserRegistrationRequestDto requestDto) {
        try {
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            LOGGER.info("Starting to send welcome email to {} on thread: {}", requestDto.getUsername(), Thread.currentThread().getName());
            mailMessage.setFrom(fromMailId);
            mailMessage.setTo(requestDto.getEmail());
            mailMessage.setSubject("Test Email from Spring Boot");
            mailMessage.setText("Hello, "+requestDto.getUsername()+". You are registered to the platform successfully.");
            javaMailSender.send(mailMessage);
            LOGGER.info("Successfully sent welcome email to {} on thread: {}", requestDto.getUsername(), Thread.currentThread().getName());
        } catch (Exception e){
            LOGGER.info("An exception occured {}",e.getMessage());
        }
    }

    @Override
    @KafkaListener(topics = "project_created", groupId = "kanban-group")
    public void sendProjectCreatedNotificationMail(ProjectCreatedEventDto eventDto) {
        try {
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            LOGGER.info("Starting to send email to {} on thread: {}", eventDto.getUserName(), Thread.currentThread().getName());
            mailMessage.setFrom(fromMailId);
            mailMessage.setTo(eventDto.getEmail());
            mailMessage.setSubject("New Project Created");
            mailMessage.setText("Hello, "+eventDto.getUserName()+". New project has been created successfully.");
            javaMailSender.send(mailMessage);
            LOGGER.info("Successfully sent notification email to {} on thread: {}", eventDto.getUserName(), Thread.currentThread().getName());
        } catch (Exception e){
            LOGGER.info("An exception occured {}",e.getMessage());
        }
    }

    @Override
    @KafkaListener(topics = "user_added", groupId = "kanban-group")
    public void sendUserAddedToProjectNotification(UserAddedEventDto userAddedEventDto) {
        try {
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            LOGGER.info("Starting to send email to {} on thread: {}", userAddedEventDto.getUserName(), Thread.currentThread().getName());
            mailMessage.setFrom(fromMailId);
            mailMessage.setTo(userAddedEventDto.getEmail());
            mailMessage.setSubject("New Project Created");
            mailMessage.setText("Hello, "+userAddedEventDto.getUserName()+". You have been added to project: "+userAddedEventDto.getProjectName());
            javaMailSender.send(mailMessage);
            LOGGER.info("Successfully sent notification email to {} on thread: {}", userAddedEventDto.getUserName(), Thread.currentThread().getName());
        } catch (Exception e){
            LOGGER.info("An exception occured {}",e.getMessage());
        }
    }

}
