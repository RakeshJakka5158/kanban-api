package com.kanbanflow.kanban_api.dto;

import com.kanbanflow.kanban_api.entity.Project;
import com.kanbanflow.kanban_api.entity.User;
import lombok.Data;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Data
public class GetProjectResponseDto {
    private String name;
    private String description;
    private String creator;
    private List<TaskDto> tasks;
    private Set<String> members;

    public static GetProjectResponseDto from(Project project) {
        GetProjectResponseDto dto = new GetProjectResponseDto();
        dto.setName(project.getName());
        dto.setDescription(project.getDescription());
        dto.setCreator(project.getCreator().getUsername());

        // Map Task entities to TaskDtos
        dto.setTasks(project.getTasks().stream()
                .map(TaskDto::from)
                .collect(Collectors.toList()));

        dto.setMembers(project.getMembers().stream()
                .map(User::getUsername)
                .collect(Collectors.toSet()));
        return dto;
    }
}
