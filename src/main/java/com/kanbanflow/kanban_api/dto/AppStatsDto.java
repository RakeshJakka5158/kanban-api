package com.kanbanflow.kanban_api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class AppStatsDto {
    private long totalUsers;
    private long totalTasks;
    private long totalProjects;
    private Date timeStamp;
}
