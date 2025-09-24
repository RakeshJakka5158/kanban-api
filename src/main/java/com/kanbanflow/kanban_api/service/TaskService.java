package com.kanbanflow.kanban_api.service;

import com.kanbanflow.kanban_api.dto.CreateTaskRequestDto;
import com.kanbanflow.kanban_api.dto.CreateTaskResponseDto;

public interface TaskService {
    CreateTaskResponseDto createTask(CreateTaskRequestDto requestDto);
}
