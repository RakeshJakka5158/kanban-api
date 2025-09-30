package com.kanbanflow.kanban_api.actuator.endpoint;

import com.kanbanflow.kanban_api.dto.AppStatsDto;
import com.kanbanflow.kanban_api.repository.ProjectRepository;
import com.kanbanflow.kanban_api.repository.TaskRepository;
import com.kanbanflow.kanban_api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Endpoint(id = "app-stats")
public class AppStatsEndpoint {
    private final UserRepository userRepository;
    private final TaskRepository taskRepository;
    private final ProjectRepository projectRepository;

    @Autowired
    public AppStatsEndpoint(UserRepository userRepository, TaskRepository taskRepository, ProjectRepository projectRepository) {
        this.userRepository = userRepository;
        this.taskRepository = taskRepository;
        this.projectRepository = projectRepository;
    }

    @ReadOperation
    public AppStatsDto getAppStats() {
        long totalUsers = userRepository.count();
        long totalTasks = taskRepository.count();
        long projectCount = projectRepository.count();
        return new AppStatsDto(totalUsers,totalTasks,projectCount,new Date());
    }
}
