package com.kanbanflow.kanban_api.service.Kafka;

import com.kanbanflow.kanban_api.dto.UserRegistrationRequestDto;

public interface KafkaProducerService {
    void sendUserCreatedEvent(UserRegistrationRequestDto requestDto);
} 
