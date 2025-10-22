package com.kanbanflow.kanban_api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserAddedEventDto {
    private String email;
    private String userName;
    private String projectName;
}
