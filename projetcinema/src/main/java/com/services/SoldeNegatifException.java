package com.services;

public class SoldeNegatifException extends Exception {
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SoldeNegatifException() {
		this("Votre solde est insuffisant");
	}
	
	public SoldeNegatifException(String message) {
		super(message);
	}

}
