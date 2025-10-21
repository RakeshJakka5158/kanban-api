package com.kanbanflow.kanban_api.dto;

import lombok.Data;

@Data
public class CreateProjectResponseDto {
    private Long projectId;
    private String projectName;
    private String description;
}
