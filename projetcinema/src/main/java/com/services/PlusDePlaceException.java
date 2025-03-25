package com.services;

public class PlusDePlaceException extends Exception {
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PlusDePlaceException() {
		this("Désolé tous les places sont réservées ");
	}

	public  PlusDePlaceException(String message) {
		super(message);
	}
}
