package com.kanbanflow.kanban_api.service.impl;

import com.kanbanflow.kanban_api.dto.CreateProjectRequestDto;
import com.kanbanflow.kanban_api.dto.CreateProjectResponseDto;
import com.kanbanflow.kanban_api.dto.GetProjectResponseDto;
import com.kanbanflow.kanban_api.dto.ProjectCreatedEventDto;
import com.kanbanflow.kanban_api.entity.Project;
import com.kanbanflow.kanban_api.entity.User;
import com.kanbanflow.kanban_api.exception.ProjectAlreadyExistsException;
import com.kanbanflow.kanban_api.exception.ResourceNotFoundException;
import com.kanbanflow.kanban_api.repository.ProjectRepository;
import com.kanbanflow.kanban_api.service.Kafka.KafkaProducerService;
import com.kanbanflow.kanban_api.service.ProjectService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ProjectServiceImpl implements ProjectService {
    private static final Logger logger = LoggerFactory.getLogger(ProjectServiceImpl.class);
    private final ProjectRepository projectRepository;
    private final KafkaProducerService kafkaProducerService;

    public ProjectServiceImpl(ProjectRepository projectRepository, KafkaProducerService kafkaProducerService) {
        this.projectRepository = projectRepository;
        this.kafkaProducerService = kafkaProducerService;
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

    @Override
    public CreateProjectResponseDto createProject(CreateProjectRequestDto requestDto, User user) {
        // Check if Project already exists in DB.
        Optional<Project> optionalProject = projectRepository.findByName(requestDto.getName());
        if(optionalProject.isPresent()){
            throw new ProjectAlreadyExistsException("Project with name: "+requestDto.getName()+" already exists.");
        }

        // Create new Project
        Project project = new Project();
        project.setName(requestDto.getName());
        project.setDescription(requestDto.getDescription());
        project.setCreator(user);

        // save Project
        Project savedProject = projectRepository.save(project);

        // Send notification via kafka
        logger.info("Sending project created event notification...");
        kafkaProducerService.sendProjectCreatedEvent(new ProjectCreatedEventDto(savedProject));

        // Set response
        CreateProjectResponseDto responseDto = new CreateProjectResponseDto();
        responseDto.setProjectId(savedProject.getId());
        responseDto.setProjectName(savedProject.getName());
        responseDto.setDescription(savedProject.getDescription());
        return responseDto;
    }
}
