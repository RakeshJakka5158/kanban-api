package com.kanbanflow.kanban_api.controller;

import com.kanbanflow.kanban_api.dto.CreateProjectRequestDto;
import com.kanbanflow.kanban_api.dto.CreateProjectResponseDto;
import com.kanbanflow.kanban_api.dto.GetProjectResponseDto;
import com.kanbanflow.kanban_api.entity.User;
import com.kanbanflow.kanban_api.service.ProjectService;
import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;


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

    @PostMapping("/create")
    public CreateProjectResponseDto createProject(@Valid @RequestBody CreateProjectRequestDto requestDto, Authentication authentication){
        // Get User from Authentication
        User user = (User) authentication.getPrincipal();
        return projectService.createProject(requestDto,user);
    }
}
