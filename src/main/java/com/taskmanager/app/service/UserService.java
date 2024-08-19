package com.taskmanager.app.service;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.taskmanager.app.entity.User;
import com.taskmanager.app.exception.EmailAlreadyTakenException;
import com.taskmanager.app.exception.UserNotFoundException;
import com.taskmanager.app.exception.UsernameAlreadyTakenException;
import com.taskmanager.app.repository.UserRepository;

@Service
public class UserService {

	private UserRepository userRepository;

	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Transactional(readOnly = true)
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	@Transactional(readOnly = true)
	public User getUserById(Long userId) {
		return userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));
	}

	@Transactional
	public User createUser(User newUser) {
		if (isUsernameTaken(newUser.getUsername())) {
			throw new UsernameAlreadyTakenException(newUser.getUsername());
		} else if (isEmailTaken(newUser.getEmail())) {
			throw new EmailAlreadyTakenException(newUser.getEmail());
		}

		newUser.setCreatedDate(new Date());

		return userRepository.save(newUser);
	}

	@Transactional
	public User updateUser(User updatedUser, Long userId) {
		User existingUser = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));

		if (updatedUser.getUsername() != null) {
			if (isUsernameTaken(updatedUser.getUsername())) {
				throw new UsernameAlreadyTakenException(updatedUser.getUsername());
			}
			existingUser.setUsername(updatedUser.getUsername());
		}

		if (updatedUser.getPassword() != null) {
			existingUser.setPassword(updatedUser.getPassword());
		}

		if (updatedUser.getEmail() != null) {
			if (isEmailTaken(updatedUser.getEmail())) {
				throw new EmailAlreadyTakenException(updatedUser.getEmail());
			}
			existingUser.setEmail(updatedUser.getEmail());
		}

		return userRepository.save(existingUser);
	}

	@Transactional
	public void deleteUserById(Long userId) {
		if (!userRepository.existsById(userId)) {
			throw new UserNotFoundException(userId);
		}
		userRepository.deleteById(userId);
	}

	public boolean isUsernameTaken(String username) {
		return userRepository.findByUsername(username).isPresent();
	}

	public boolean isEmailTaken(String email) {
		return userRepository.findByEmail(email).isPresent();
	}

}
