package com.kanbanflow.kanban_api.service;

import com.kanbanflow.kanban_api.dto.CreateProjectRequestDto;
import com.kanbanflow.kanban_api.dto.CreateProjectResponseDto;
import com.kanbanflow.kanban_api.dto.GetProjectResponseDto;
import com.kanbanflow.kanban_api.entity.User;
import jakarta.validation.Valid;

public interface ProjectService {
    GetProjectResponseDto getProjectById(Long projectId);

    CreateProjectResponseDto createProject(CreateProjectRequestDto requestDto, User user);
}
