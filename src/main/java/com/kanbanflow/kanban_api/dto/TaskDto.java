package com.kanbanflow.kanban_api.dto;

import com.kanbanflow.kanban_api.entity.Task;
import lombok.Data;

@Data
public class TaskDto {
    private Long id;
    private String title;
    private String status;
    private String assigneeUsername;

    public static TaskDto from(Task task) {
        TaskDto dto = new TaskDto();
        dto.setId(task.getId());
        dto.setTitle(task.getTitle());
        dto.setStatus(task.getStatus());
        if (task.getAssignee() != null) {
            dto.setAssigneeUsername(task.getAssignee().getUsername());
        }
        return dto;
    }
}
