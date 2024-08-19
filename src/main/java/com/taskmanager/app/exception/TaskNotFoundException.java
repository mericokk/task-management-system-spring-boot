package com.taskmanager.app.exception;

public class TaskNotFoundException extends RuntimeException {

	public TaskNotFoundException(Long taskId) {
		super(String.format("Could not find task with ID: %d", taskId));
	}

}