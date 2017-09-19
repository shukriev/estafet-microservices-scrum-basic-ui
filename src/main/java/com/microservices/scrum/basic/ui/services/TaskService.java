package com.microservices.scrum.basic.ui.services;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.microservices.scrum.basic.ui.model.Task;

@Service
public class TaskService {

	public void claim(int taskId) {
		new RestTemplate().postForObject("http://localhost:8080/sprint-board-api/task/{id}/claim", null, Task.class,
				taskId);
	}

	public void complete(int taskId) {
		new RestTemplate().postForObject("http://localhost:8080/sprint-board-api/task/{id}/complete", null, Task.class,
				taskId);
	}

}
