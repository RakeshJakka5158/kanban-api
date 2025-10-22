package com.kanbanflow.kanban_api.service;

import com.kanbanflow.kanban_api.dto.ProjectCreatedEventDto;
import com.kanbanflow.kanban_api.dto.UserAddedEventDto;
import com.kanbanflow.kanban_api.dto.UserRegistrationRequestDto;

public interface NotificationService {
    void sendWelcomeEmail(UserRegistrationRequestDto requestDto);
    void sendProjectCreatedNotificationMail(ProjectCreatedEventDto eventDto);
    void sendUserAddedToProjectNotification(UserAddedEventDto userAddedEventDto);
}
