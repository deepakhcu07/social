package com.spgroup.friend.exception;

public class UserNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3950114293715595224L;
	
	public UserNotFoundException(String message) {
		super(message);
	}
	

}
