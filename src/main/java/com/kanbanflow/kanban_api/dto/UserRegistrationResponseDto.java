package com.kanbanflow.kanban_api.dto;

import lombok.Data;

@Data
public class UserRegistrationResponseDto {
    private String userName;
    private int status;
}
