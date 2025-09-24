package com.kanbanflow.kanban_api.service.impl;

import com.kanbanflow.kanban_api.dto.GetProjectResponseDto;
import com.kanbanflow.kanban_api.entity.Project;
import com.kanbanflow.kanban_api.repository.ProjectRepository;
import com.kanbanflow.kanban_api.service.ProjectService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProjectServiceImpl implements ProjectService {
    private final ProjectRepository projectRepository;

    public ProjectServiceImpl(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public GetProjectResponseDto getProjectById(Long projectId) {
        Project project = projectRepository.findById(projectId).orElse(new Project());
        return GetProjectResponseDto.from(project);
    }
}
