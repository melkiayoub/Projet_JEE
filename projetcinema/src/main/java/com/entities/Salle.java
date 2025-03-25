package com.entities;

import java.io.Serializable;

import jakarta.json.bind.annotation.JsonbTransient;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity

public class Salle implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private String adresse;
	private int capacite;
	
	@OneToOne(mappedBy = "salle")
	@JsonbTransient
	private SalleProg salleProg;
	
	public Salle() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Salle( String name, String adresse, int capacite, SalleProg salleProg) {
		super();
		
		this.name = name;
		this.adresse = adresse;
		this.capacite = capacite;
		this.salleProg = salleProg;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	public int getCapacite() {
		return capacite;
	}
	public void setCapacite(int capacite) {
		this.capacite = capacite;
	}

	public SalleProg getSalleProg() {
		return salleProg;
	}

	public void setSalleProg(SalleProg salleProg) {
		this.salleProg = salleProg;
	}

	@Override
	public String toString() {
		return "Salle [id=" + id + ", name=" + name + ", adresse=" + adresse + ", capacite=" + capacite + ", salleProg="
				+ salleProg + "]";
	}
	
		

}
