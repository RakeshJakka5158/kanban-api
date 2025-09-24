package com.kanbanflow.kanban_api.dto;

import com.kanbanflow.kanban_api.entity.Task;
import lombok.Data;

@Data
public class CreateTaskResponseDto {
    private Long taskId;
    private String title;
    private String projectName;

    public static CreateTaskResponseDto from (Task task) {
        CreateTaskResponseDto responseDto = new CreateTaskResponseDto();
        responseDto.setTaskId(task.getId());
        responseDto.setTitle(task.getTitle());
        responseDto.setProjectName(task.getProject().getName());
        return responseDto;
    }
}
