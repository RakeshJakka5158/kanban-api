package com.kanbanflow.kanban_api.service.impl;

import com.kanbanflow.kanban_api.dto.CreateTaskRequestDto;
import com.kanbanflow.kanban_api.dto.CreateTaskResponseDto;
import com.kanbanflow.kanban_api.entity.Project;
import com.kanbanflow.kanban_api.entity.Task;
import com.kanbanflow.kanban_api.entity.User;
import com.kanbanflow.kanban_api.exception.ResourceNotFoundException;
import com.kanbanflow.kanban_api.repository.ProjectRepository;
import com.kanbanflow.kanban_api.repository.TaskRepository;
import com.kanbanflow.kanban_api.repository.UserRepository;
import com.kanbanflow.kanban_api.service.TaskService;
import org.springframework.stereotype.Service;

@Service
public class TaskServiceImpl implements TaskService {

    private final UserRepository userRepository;
    private final TaskRepository taskRepository;
    private final ProjectRepository projectRepository;

    public TaskServiceImpl(UserRepository userRepository, TaskRepository taskRepository, ProjectRepository projectRepository) {
        this.userRepository = userRepository;
        this.taskRepository = taskRepository;
        this.projectRepository = projectRepository;
    }

    @Override
    public CreateTaskResponseDto createTask(CreateTaskRequestDto requestDto) {

        Task task = new Task();
        task.setTitle(requestDto.getTitle());
        task.setDescription(requestDto.getDescription());
        task.setStatus(requestDto.getStatus());
        task.setDueDate(requestDto.getDueDate());
        // Get User from userId
        User user = userRepository.findById(requestDto.getUserId()).orElseThrow(() -> new ResourceNotFoundException("User with the given userId: "+requestDto.getUserId()+"not found."));
        task.setAssignee(user);

        // Get Project from projectId
        Project project = projectRepository.findById(requestDto.getProjectId()).orElseThrow(() -> new ResourceNotFoundException("Project with given projectId: "+requestDto.getProjectId()+"not found."));
        task.setProject(project);

        // Save task
        Task createdTask = taskRepository.save(task);

        return CreateTaskResponseDto.from(createdTask);
    }
}
