package com.kanbanflow.kanban_api.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class CreateTaskRequestDto {
    @NotBlank(message = "Task title cannot be null")
    private String title;
    private String description;
    @NotNull(message = "Project ID cannot be null")
    private Long projectId;
    @Future(message = "Due date must be in future")
    private LocalDate dueDate;
    @NotNull(message = "Assignee cannot be null")
    private Long userId;
    @NotNull(message = "Task status cannot be null")
    private String status;
}
