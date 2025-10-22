package com.kanbanflow.kanban_api.service;

import com.kanbanflow.kanban_api.dto.ProjectCreatedEventDto;
import com.kanbanflow.kanban_api.dto.UserAddedEventDto;
import com.kanbanflow.kanban_api.dto.UserRegistrationRequestDto;

public interface KafkaProducerService {
    void sendUserCreatedEvent(UserRegistrationRequestDto requestDto);
    void sendProjectCreatedEvent(ProjectCreatedEventDto eventDto);
    void sendUserAddedToProjectEvent(UserAddedEventDto userAddedEventDto);
} 
