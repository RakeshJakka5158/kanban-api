package com.kanbanflow.kanban_api.dto;

import com.kanbanflow.kanban_api.entity.Project;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectCreatedEventDto {
    private String projectName;
    private String userName;
    private String email;

    public ProjectCreatedEventDto(Project project){
        this.projectName = project.getName();
        this.userName = project.getCreator().getUsername();
        this.email = project.getCreator().getEmail();
    }
}
