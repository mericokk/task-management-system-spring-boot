package com.taskmanager.app.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.taskmanager.app.entity.Task;
import com.taskmanager.app.entity.User;
import com.taskmanager.app.exception.TaskNotFoundException;
import com.taskmanager.app.exception.UserNotFoundException;
import com.taskmanager.app.repository.TaskRepository;
import com.taskmanager.app.repository.UserRepository;

@Service
public class ManagementService {

	private TaskRepository taskRepository;
	private UserRepository userRepository;

	public ManagementService(TaskRepository taskRepository, UserRepository userRepository) {
		this.taskRepository = taskRepository;
		this.userRepository = userRepository;
	}

	@Transactional
	public void addUserToTask(Long taskId, Long userId) {

		Task task = taskRepository.findById(taskId).orElseThrow(() -> new TaskNotFoundException(taskId));
		User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));

		task.getUsers().add(user);
		user.getTasks().add(task);

		taskRepository.save(task);
		userRepository.save(user);

	}

	@Transactional
	public void removeUserFromTask(Long taskId, Long userId) {

		Task task = taskRepository.findById(taskId).orElseThrow(() -> new TaskNotFoundException(taskId));
		User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));

		task.getUsers().remove(user);
		user.getTasks().remove(task);

		taskRepository.save(task);
		userRepository.save(user);

	}

}
