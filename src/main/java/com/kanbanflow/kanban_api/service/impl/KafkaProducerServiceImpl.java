package com.kanbanflow.kanban_api.service.impl;
import com.kanbanflow.kanban_api.dto.UserRegistrationRequestDto;
import org.slf4j.Logger;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.kanbanflow.kanban_api.service.Kafka.KafkaProducerService;

@Service
public class KafkaProducerServiceImpl implements KafkaProducerService {
    private static final String TOPIC = "user_created";
    private static final Logger logger = LoggerFactory.getLogger(KafkaProducerServiceImpl.class);
    private final KafkaTemplate<String, UserRegistrationRequestDto> kafkaTemplate;
    
    @Autowired
    public KafkaProducerServiceImpl(KafkaTemplate<String, UserRegistrationRequestDto> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void sendUserCreatedEvent(UserRegistrationRequestDto requestDto) {
        logger.info("Sending topic to kafka ....");
        this.kafkaTemplate.send(TOPIC, requestDto);
    }
    
}
