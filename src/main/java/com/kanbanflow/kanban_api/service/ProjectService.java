package com.kanbanflow.kanban_api.service;

import com.kanbanflow.kanban_api.dto.CreateProjectRequestDto;
import com.kanbanflow.kanban_api.dto.CreateProjectResponseDto;
import com.kanbanflow.kanban_api.dto.GetProjectResponseDto;
import com.kanbanflow.kanban_api.dto.UpdateProjectRequestDto;
import com.kanbanflow.kanban_api.entity.User;

import java.util.List;

public interface ProjectService {
    GetProjectResponseDto getProjectById(Long projectId);

    CreateProjectResponseDto createProject(CreateProjectRequestDto requestDto, User user);

    List<GetProjectResponseDto> getAllProjects();

    GetProjectResponseDto updateProjectMembers(Long id, UpdateProjectRequestDto requestDto);
}
