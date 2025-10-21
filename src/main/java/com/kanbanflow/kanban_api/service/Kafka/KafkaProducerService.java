package com.kanbanflow.kanban_api.service.Kafka;

import com.kanbanflow.kanban_api.dto.ProjectCreatedEventDto;
import com.kanbanflow.kanban_api.dto.UserRegistrationRequestDto;
import com.kanbanflow.kanban_api.entity.Project;

public interface KafkaProducerService {
    void sendUserCreatedEvent(UserRegistrationRequestDto requestDto);
    void sendProjectCreatedEvent(ProjectCreatedEventDto eventDto);
} 
