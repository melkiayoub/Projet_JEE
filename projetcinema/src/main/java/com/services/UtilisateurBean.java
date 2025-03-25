package com.services;

import com.entities.Compte;

import jakarta.ejb.Stateful;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

@Stateful
public class UtilisateurBean implements Utilisateur{
	@PersistenceContext
	private EntityManager em = null;
	private int user_id;
	public UtilisateurBean() {
	super();
	}

	@Override
	public void init(String name, String password)throws UserNotFoundException {
		TypedQuery<Compte> q=em.createQuery("select c from Compte c where c.name= :name",Compte.class);
		q.setParameter("name",name);
		Compte c=q.getSingleResult();
		if((c.getPassword().equals(password))&& c!=null) {
			user_id=c.getId();
		}else
			throw new UserNotFoundException();
		
		
		
	}

	@Override
	public String getName() {
		TypedQuery<Compte> q=em.createQuery("select c from Compte c where c.id= :user_id",Compte.class);
		q.setParameter("user_id",user_id);
		Compte c=q.getSingleResult();
		return c.getName();
		
		
	}

	@Override
	public float solde() {
		TypedQuery<Compte> q=em.createQuery("select c from Compte c where c.id= :user_id",Compte.class);
		q.setParameter("user_id",user_id);
		Compte c=q.getSingleResult();
		return c.getSolde();
		
		
		
	}

	@Override
	public void debit(float somme) throws SoldeNegatifException {
		TypedQuery<Compte> q=em.createQuery("select c from Compte c where c.id=:user_id",Compte.class);
		q.setParameter("user_id",user_id);
		Compte c=q.getSingleResult();
		if(c.getSolde()-somme>0) {
			c.setSolde(c.getSolde()-somme);
			em.merge(c);
		}else
			throw new SoldeNegatifException();
		
		
		
	}

}
