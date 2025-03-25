package com.entities;

import java.io.Serializable;
import java.util.List;

import jakarta.json.bind.annotation.JsonbTransient;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;



@Entity
public class Film implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String nom;
	
	@OneToMany(mappedBy = "film")
	@JsonbTransient
	private List<SalleProg> listSalleProg;

	public Film() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Film( String nom) {
		super();
		
		this.nom = nom;
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public List<SalleProg> getListSalleProg() {
		return listSalleProg;
	}

	public void setListSalleProg(List<SalleProg> listSalleProg) {
		this.listSalleProg = listSalleProg;
	}

	@Override
	public String toString() {
		return "Film [id=" + id + ", nom=" + nom + ", listSalleProg=" + listSalleProg + "]";
	}
	
	
	

}
