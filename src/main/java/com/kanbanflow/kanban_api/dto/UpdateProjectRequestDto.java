package com.kanbanflow.kanban_api.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.Set;

@Data
public class UpdateProjectRequestDto {
    @NotEmpty(message = "User Id list cannot be empty")
    private Set<Long> userIds;
}
