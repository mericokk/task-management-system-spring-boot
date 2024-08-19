package com.taskmanager.app.service;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.taskmanager.app.repository.TaskRepository;

import com.taskmanager.app.entity.Task;
import com.taskmanager.app.exception.TaskNotFoundException;

@Service
public class TaskService {

	private TaskRepository taskRepository;

	public TaskService(TaskRepository taskRepository) {
		this.taskRepository = taskRepository;
	}

	@Transactional(readOnly = true)
	public List<Task> getAllTasks() {
		return taskRepository.findAll();
	}

	@Transactional(readOnly = true)
	public Task getTaskById(Long taskId) {
		return taskRepository.findById(taskId).orElseThrow(() -> new TaskNotFoundException(taskId));
	}

	@Transactional
	public Task createTask(Task newTask) {
		newTask.setCreatedDate(new Date());

		return taskRepository.save(newTask);
	}

	@Transactional
	public Task updateTask(Task updatedTask, Long taskId) {
		Task existingTask = taskRepository.findById(taskId).orElseThrow(() -> new TaskNotFoundException(taskId));

		existingTask.setTitle(updatedTask.getTitle());
		existingTask.setDescription(updatedTask.getDescription());
		existingTask.setStatus(updatedTask.getStatus());

		return taskRepository.save(existingTask);
	}

	@Transactional
	public void deleteTaskById(Long taskId) {
		if (!taskRepository.existsById(taskId)) {
			throw new TaskNotFoundException(taskId);
		}

		taskRepository.deleteById(taskId);
	}

}
