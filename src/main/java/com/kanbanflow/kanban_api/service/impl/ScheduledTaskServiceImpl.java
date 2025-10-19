package com.kanbanflow.kanban_api.service.impl;

import com.kanbanflow.kanban_api.repository.ProjectRepository;
import com.kanbanflow.kanban_api.repository.UserRepository;
import com.kanbanflow.kanban_api.service.ScheduledTaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class ScheduledTaskServiceImpl implements ScheduledTaskService {
    private static final Logger logger = LoggerFactory.getLogger(ScheduledTaskServiceImpl.class);

    private final UserRepository userRepository;
    private final ProjectRepository projectRepository;

    @Autowired
    public ScheduledTaskServiceImpl(UserRepository userRepository, ProjectRepository projectRepository) {
        this.userRepository = userRepository;
        this.projectRepository = projectRepository;
    }


    @Override
    @Scheduled(cron = "0 * 1 * * *")
    public void generateHourlyReport() {
        long userCount = userRepository.count();
        long projectCount = projectRepository.count();

        logger.info("--- [SCHEDULED TASK] Hourly Status Report ---");
        logger.info("Current Thread: {}", Thread.currentThread().getName());
        logger.info("Total Registered Users: {}", userCount);
        logger.info("Total Active Projects: {}", projectCount);
        logger.info("--- Report End ---");

    }
}
