package com.kanbanflow.kanban_api.service.impl;

import com.kanbanflow.kanban_api.dto.GetProjectResponseDto;
import com.kanbanflow.kanban_api.entity.Project;
import com.kanbanflow.kanban_api.exception.ResourceNotFoundException;
import com.kanbanflow.kanban_api.repository.ProjectRepository;
import com.kanbanflow.kanban_api.service.ProjectService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProjectServiceImpl implements ProjectService {
    private static final Logger logger = LoggerFactory.getLogger(ProjectServiceImpl.class);
    private final ProjectRepository projectRepository;

    public ProjectServiceImpl(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Override
    @Cacheable(value = "projects", key = "#projectId")
    @Transactional(readOnly = true)
    public GetProjectResponseDto getProjectById(Long projectId) {
        logger.info("--- Fetching project with id: {} from DATABASE. ---", projectId);
        Project project = projectRepository.findById(projectId)
                    .orElseThrow(()->new ResourceNotFoundException("Project not found with id: " + projectId));
        return GetProjectResponseDto.from(project);
    }
}
