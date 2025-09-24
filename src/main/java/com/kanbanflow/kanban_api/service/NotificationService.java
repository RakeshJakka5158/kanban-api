package com.kanbanflow.kanban_api.service;

import com.kanbanflow.kanban_api.dto.UserRegistrationRequestDto;

public interface NotificationService {
    public void sendWelcomeEmail(UserRegistrationRequestDto requestDto);
}
