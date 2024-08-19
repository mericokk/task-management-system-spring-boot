package com.taskmanager.app.exception;

public class UsernameAlreadyTakenException extends RuntimeException {

	public UsernameAlreadyTakenException(String username) {
		super(String.format("Username %s is already taken", username));
	}

}
