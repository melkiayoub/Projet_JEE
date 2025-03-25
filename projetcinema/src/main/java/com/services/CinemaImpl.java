package com.services;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.entities.Film;
import com.entities.SalleProg;
import com.entities.Seance;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;


@Stateless

public class CinemaImpl implements Cinema{
	@PersistenceContext(unitName="cinema_Unit")
	EntityManager em;

	@Override
	public Set<Film> list() {
		TypedQuery<Film> q = em.createQuery("select f from Film f",Film.class);
		List<Film> f=q.getResultList();
		Set<Film> s= f.stream().collect(Collectors.toSet());

	    return s;
				
	}

	@Override
	public Set<Film> findByPattern(String pattern) {
		TypedQuery<Film> q =em.createQuery("select f from Film f where f.nom LIKE :pattern",Film.class );
		q.setParameter("pattern","%"+pattern+"%");
		List<Film> f=q.getResultList();
		Set<Film> s= f.stream().collect(Collectors.toSet());
		return s;

	}

	@Override
	public Film findFilmById(int id) {
		TypedQuery<Film> q =em.createQuery("select f from Film f where f.id = :id",Film.class );
		q.setParameter("id",id);
		Film f=q.getSingleResult();
		return f;
		
	}

	@Override
	public void reserv(Seance seance, Utilisateur u)throws PlusDePlaceException,SoldeNegatifException {
		if((u.solde()-seance.getTarif()>0) && (seance.getPlaces()<seance.getSalleProg().getSalle().getCapacite()) ) {
			seance.setPlaces(seance.getPlaces()+1);
			try {
				u.debit(seance.getTarif());
			} catch (SoldeNegatifException e) {
				System.out.println(e.getMessage());
			}
			em.merge(seance);
			
		}else
			throw new PlusDePlaceException();throw new SoldeNegatifException();
		
	}

	@Override
	public Set<SalleProg> getAllSalleProg() {
		TypedQuery<SalleProg> q =em.createQuery("select s from SalleProg s",SalleProg.class );
		List<SalleProg> f=q.getResultList();
		Set<SalleProg> s= f.stream().collect(Collectors.toSet());
		return s;
	}

	@Override
	public Film createFilm(String name) {
		Film f=new Film(name);
		return f;
	}
	@Override
	public void ajouterFilm(Film film) {
		
		em.persist(film);
		
	}

	@Override
	public void updateFilm(Film film) {
		em.merge(film); 
		
	}

	@Override
	public float getTarif(int id) {
		TypedQuery<Seance> q=em.createQuery("select s from Seance s where s.id = :id",Seance.class);
		q.setParameter("id",id);
		Seance s=q.getSingleResult();
		float tarif=s.getTarif();
		
		return tarif;
		
		
	}

	@Override
	public Set<Seance> getAllSeance() {
		TypedQuery<Seance> q = em.createQuery("select s from Seance s",Seance.class);
		List<Seance> f=q.getResultList();
		Set<Seance> s= f.stream().collect(Collectors.toSet());

	    return s;
	}

	@Override
	public Seance getSeanceById(int id) {
		TypedQuery<Seance> q = em.createQuery("select s from Seance s where s.id = :id",Seance.class);
		q.setParameter("id",id);
		Seance s =q.getSingleResult();
		return s;
	}

	

}
