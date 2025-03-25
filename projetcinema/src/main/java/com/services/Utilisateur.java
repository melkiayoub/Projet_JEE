package com.services;

import jakarta.ejb.Remote;

@Remote

public interface Utilisateur {
	public void init(String name,String password) throws UserNotFoundException;
	public String getName();
	public float solde();
	public void debit(float somme) throws SoldeNegatifException;

}
