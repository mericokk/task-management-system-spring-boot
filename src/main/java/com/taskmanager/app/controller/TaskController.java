package com.taskmanager.app.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.taskmanager.app.entity.Task;
import com.taskmanager.app.service.TaskService;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

	private TaskService taskService;

	public TaskController(TaskService taskService) {
		this.taskService = taskService;
	}

	@GetMapping
	public ResponseEntity<List<Task>> getAllTasks() {
		List<Task> tasks = taskService.getAllTasks();
		return ResponseEntity.ok(tasks);
	}

	@GetMapping("/{taskId}")
	public ResponseEntity<Task> getTaskById(@PathVariable Long taskId) {
		Task task = taskService.getTaskById(taskId);
		return ResponseEntity.ok(task);
	}

	@PostMapping
	public ResponseEntity<Task> createTask(@RequestBody Task newTask) {
		Task createdTask = taskService.createTask(newTask);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdTask);
	}

	@PutMapping("/{taskId}")
	public ResponseEntity<Task> updateTask(@RequestBody Task updatedTask, @PathVariable Long taskId) {
		Task task = taskService.updateTask(updatedTask, taskId);
		return ResponseEntity.ok(task);
	}

	@DeleteMapping("/{taskId}")
	public ResponseEntity<Void> deleteTask(@PathVariable Long taskId) {
		taskService.deleteTaskById(taskId);
		return ResponseEntity.noContent().build();
	}

}
