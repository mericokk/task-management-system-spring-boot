package com.taskmanager.app.exception;

public class UserNotFoundException extends RuntimeException {

	public UserNotFoundException(Long userId) {
		super(String.format("Could not find user with ID: %d", userId));
	}

}
