package com.kanbanflow.kanban_api.controller;

import com.kanbanflow.kanban_api.dto.CreateTaskRequestDto;
import com.kanbanflow.kanban_api.dto.CreateTaskResponseDto;
import com.kanbanflow.kanban_api.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping("/create")
    public ResponseEntity<CreateTaskResponseDto> createTask (@RequestBody @Valid CreateTaskRequestDto requestDto) {
        return new ResponseEntity<>(taskService.createTask(requestDto), HttpStatus.CREATED);
    }
}
