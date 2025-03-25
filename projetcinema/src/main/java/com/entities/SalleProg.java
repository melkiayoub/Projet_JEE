package com.entities;

import java.io.Serializable;
import java.util.List;

import jakarta.json.bind.annotation.JsonbTransient;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class SalleProg implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@OneToOne
	
	private Salle salle;
	
	@OneToMany(mappedBy = "salleProg")
	@JsonbTransient
	
	
	private List<Seance> seanceList;
	
	@ManyToOne
	private Film film;
	

	public SalleProg(Salle salle, List<Seance> seanceList, Film film) {
		super();
		this.salle = salle;
		this.seanceList = seanceList;
		this.film = film;
	}

	public SalleProg() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Salle getSalle() {
		return salle;
	}

	public void setSalle(Salle salle) {
		this.salle = salle;
	}

	public List<Seance> getSeanceList() {
		return seanceList;
	}

	public void setSeanceList(List<Seance> seanceList) {
		this.seanceList = seanceList;
	}

	public Film getFilm() {
		return film;
	}

	public void setFilm(Film film) {
		this.film = film;
	}
	

}
