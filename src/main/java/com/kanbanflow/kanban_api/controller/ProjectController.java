package com.kanbanflow.kanban_api.controller;

import com.kanbanflow.kanban_api.dto.GetProjectResponseDto;
import com.kanbanflow.kanban_api.entity.Project;
import com.kanbanflow.kanban_api.service.ProjectService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping("/{id}")
    public GetProjectResponseDto getProjectById(@PathVariable("id") Long projectId) {
        return projectService.getProjectById(projectId);
    }
}
