package com.services;

public class UserNotFoundException extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UserNotFoundException() {
		this("Veuillez vérifier vos données");
	}
	
	public UserNotFoundException(String message) {
		super(message);
	}

}
