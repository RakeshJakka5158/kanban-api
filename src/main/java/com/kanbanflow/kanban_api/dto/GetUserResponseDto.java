package com.kanbanflow.kanban_api.dto;

import com.kanbanflow.kanban_api.entity.User;
import lombok.Data;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Data
public class GetUserResponseDto {
    private String username;
    private String email;
    private String role;
    private Set<ProjectDto> assignedProjects;
    private List<TaskDto> assignedTasks;

    public static GetUserResponseDto from(User user){
        GetUserResponseDto responseDto = new GetUserResponseDto();
        responseDto.setUsername(user.getUsername());
        responseDto.setEmail(user.getEmail());
        responseDto.setRole(user.getRole());
        responseDto.setAssignedProjects(
                user.getAssignedProjects().stream().map(ProjectDto::from).collect(Collectors.toSet())
        );
        responseDto.setAssignedTasks(
                user.getAssignedTasks().stream().map(TaskDto::from).collect(Collectors.toList())
        );
        return responseDto;
    }
}
