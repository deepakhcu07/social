package com.spgroup.friend.exception;

public class ResourceAlreadyExistException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1307302341938988222L;
	
	public ResourceAlreadyExistException() {
		super();
	}
	
	public ResourceAlreadyExistException(String message) {
		super(message);
	}

}
