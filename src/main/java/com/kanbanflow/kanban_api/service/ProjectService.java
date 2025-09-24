package com.kanbanflow.kanban_api.service;

import com.kanbanflow.kanban_api.dto.GetProjectResponseDto;
import com.kanbanflow.kanban_api.entity.Project;

import java.util.Optional;

public interface ProjectService {
    GetProjectResponseDto getProjectById(Long projectId);
}
