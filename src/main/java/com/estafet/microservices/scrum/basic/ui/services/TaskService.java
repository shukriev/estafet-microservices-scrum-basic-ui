package com.estafet.microservices.scrum.basic.ui.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.estafet.microservices.scrum.basic.ui.model.Story;
import com.estafet.microservices.scrum.basic.ui.model.Task;

@Service
public class TaskService {

	@Autowired
	private SprintService sprintService;
	
	public void claim(int taskId) {
		new RestTemplate().postForObject(System.getenv("TASK_API_SERVICE_URI") + "/task/{id}/claim", null,
				Task.class, taskId);
	}

	public void complete(int sprintId, int taskId) {
		String lastSprintDay = sprintService.getLastSprintDay(sprintId);
		new RestTemplate().postForObject(System.getenv("TASK_API_SERVICE_URI") + "/task/{id}/complete", lastSprintDay,
				Task.class, taskId);
	}

	public Task getTask(int taskId) {
		return new RestTemplate().getForObject(System.getenv("TASK_API_SERVICE_URI") + "/task/{id}", Task.class,
				taskId);
	}

	public void updateRemainingTime(int taskId, Task task) {
		new RestTemplate().put(System.getenv("TASK_API_SERVICE_URI") + "/task/{id}/remainingHours", task, taskId);
	}

	public void addTask(int storyId, Task task) {
		new RestTemplate().postForObject(System.getenv("TASK_API_SERVICE_URI") + "/story/{id}/task", task, Story.class,
				storyId);
	}

}