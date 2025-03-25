package com.services;

import java.util.Set;

import com.entities.Film;
import com.entities.SalleProg;
import com.entities.Seance;

import jakarta.ejb.Remote;

@Remote

public interface Cinema {
	
	public Set<Film> list();
	public Set<Film> findByPattern(String pattern);
	public Film findFilmById(int id);
	public void reserv(Seance seance, Utilisateur u) throws PlusDePlaceException, SoldeNegatifException;
	public Set<Seance> getAllSeance();
	public Set<SalleProg> getAllSalleProg();
	public Film createFilm(String name);
	public void ajouterFilm(Film film);
	public void updateFilm(Film film);
	public float getTarif(int id);
	public Seance getSeanceById(int id);
	
	
	

}
