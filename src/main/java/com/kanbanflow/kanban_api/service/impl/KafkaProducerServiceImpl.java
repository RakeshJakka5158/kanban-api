package com.kanbanflow.kanban_api.service.impl;
import com.kanbanflow.kanban_api.dto.ProjectCreatedEventDto;
import com.kanbanflow.kanban_api.dto.UserAddedEventDto;
import com.kanbanflow.kanban_api.dto.UserRegistrationRequestDto;
import org.slf4j.Logger;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.kanbanflow.kanban_api.service.KafkaProducerService;

@Service
public class KafkaProducerServiceImpl implements KafkaProducerService {
    private static final String USER_CREATED_TOPIC = "user_created";
    private static final String PROJECT_CREATED_TOPIC = "project_created";
    private static final String USER_ADDED_TOPIC = "user_added";
    private static final Logger logger = LoggerFactory.getLogger(KafkaProducerServiceImpl.class);
    private final KafkaTemplate<String, Object> kafkaTemplate;
    
    @Autowired
    public KafkaProducerServiceImpl(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void sendUserCreatedEvent(UserRegistrationRequestDto requestDto) {
        logger.info("Sending user created topic to kafka ....");
        this.kafkaTemplate.send(USER_CREATED_TOPIC, requestDto);
    }

    @Override
    public void sendProjectCreatedEvent(ProjectCreatedEventDto eventDto) {
        logger.info("Sending project created topic to Kafka ...");
        this.kafkaTemplate.send(PROJECT_CREATED_TOPIC,eventDto);
    }

    @Override
    public void sendUserAddedToProjectEvent(UserAddedEventDto userAddedEventDto) {
        logger.info("Sending user added to project topic to kafka ....");
        this.kafkaTemplate.send(USER_ADDED_TOPIC, userAddedEventDto);
    }


}
