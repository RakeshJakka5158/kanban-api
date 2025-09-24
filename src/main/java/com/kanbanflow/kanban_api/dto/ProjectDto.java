package com.kanbanflow.kanban_api.dto;

import com.kanbanflow.kanban_api.entity.Project;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class ProjectDto {
    private String name;
    private String description;
    private String creator;
    private List<TaskDto> tasksList;

    public static ProjectDto from(Project project){
        ProjectDto projectDto = new ProjectDto();
        projectDto.setName(project.getName());
        projectDto.setDescription(project.getDescription());
        projectDto.setCreator(projectDto.getCreator());
        projectDto.setTasksList(project.getTasks().stream().map(TaskDto::from).collect(Collectors.toList()));
        return projectDto;
    }

}
