package com.kanbanflow.kanban_api.service;

import com.kanbanflow.kanban_api.dto.GetProjectResponseDto;

public interface ProjectService {
    GetProjectResponseDto getProjectById(Long projectId);
}
