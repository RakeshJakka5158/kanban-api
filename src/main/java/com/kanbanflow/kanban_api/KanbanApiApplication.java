package com.kanbanflow.kanban_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class KanbanApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(KanbanApiApplication.class, args);
	}

}
