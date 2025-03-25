package com.entities;

import java.io.Serializable;
import java.util.Date;

import jakarta.json.bind.annotation.JsonbTransient;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Seance implements Serializable {
	
	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	private int id;
	private Date horaire;
	private int places;
	private float tarif;
	@ManyToOne
	
	private SalleProg salleProg;
	public Seance() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Seance( Date horaire, int places, float tarif, SalleProg salleProg) {
		super();
		this.horaire = horaire;
		this.places = places;
		this.tarif = tarif;
		this.salleProg = salleProg;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getHoraire() {
		return horaire;
	}
	public void setHoraire(Date horaire) {
		this.horaire = horaire;
	}
	public int getPlaces() {
		return places;
	}
	public void setPlaces(int places) {
		this.places = places;
	}
	public float getTarif() {
		return tarif;
	}
	public void setTarif(float tarif) {
		this.tarif = tarif;
	}
	public SalleProg getSalleProg() {
		return salleProg;
	}
	public void setSalleProg(SalleProg salleProg) {
		this.salleProg = salleProg;
	}
	@Override
	public String toString() {
		return "Seance [id=" + id + ", horaire=" + horaire + ", places=" + places + ", tarif=" + tarif + ", salleProg="
				+ salleProg + "]";
	}
	
	

}
