package com.taskmanager.app.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.taskmanager.app.service.ManagementService;

@RestController
@RequestMapping("/api/tasks")
public class ManagementController {

	private ManagementService managementService;

	public ManagementController(ManagementService managementService) {
		this.managementService = managementService;
	}

	@PostMapping("/{taskId}/users/{userId}")
	public ResponseEntity<String> addUserToTask(@PathVariable Long taskId, @PathVariable Long userId) {

		managementService.addUserToTask(taskId, userId);

		return ResponseEntity
				.ok(String.format("User with ID %d has been successfully added to Task with ID %d", userId, taskId));

	}

	@DeleteMapping("/{taskId}/users/{userId}")
	public ResponseEntity<String> removeUserFromTask(@PathVariable Long taskId, @PathVariable Long userId) {

		managementService.removeUserFromTask(taskId, userId);

		return ResponseEntity.ok(
				String.format("User with ID %d has been successfully removed from Task with ID %d", userId, taskId));

	}

}
