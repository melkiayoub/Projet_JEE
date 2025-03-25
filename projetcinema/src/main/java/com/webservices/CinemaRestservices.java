package com.webservices;

import java.util.List;
import java.util.Set;


import com.entities.Film;
import com.entities.SalleProg;
import com.entities.Seance;
import com.services.Cinema;
import com.services.PlusDePlaceException;
import com.services.SoldeNegatifException;
import com.services.UserNotFoundException;
import com.services.Utilisateur;

import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.jws.WebParam;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Stateless
@Path("CinemaRest")

public class CinemaRestservices {
	
	@EJB  Cinema cinema;
	@EJB Utilisateur utilisateur;
	
	 @GET
	 @Path("films")
	 @Produces(MediaType.APPLICATION_JSON) 
	   public Set<Film> getAllFilmssWEB(){
		 return cinema.list();
		
	 }
	 
	 @GET
	 @Path("/films/{pattern}")
	 @Produces(MediaType.APPLICATION_JSON)
	  public Set<Film > findByPatternWEB(@PathParam(value="pattern") String pattern) {
		 return cinema.findByPattern(pattern);
		 
	        
	    }
	 @GET
	 @Path("/salleProg")
	 @Produces(MediaType.APPLICATION_JSON)
	  public Set<SalleProg > getAllSalleProgWEB() {
		 return cinema.getAllSalleProg();
		 
	        
	    }
	 @GET
	 @Path("/film/{id}")
	 @Produces(MediaType.APPLICATION_JSON) 
	public Film findFilmByIdWEB(@PathParam(value="id") int id){
		 return cinema.findFilmById(id);
		
	 }
	 @GET
	 @Path("seances")
	 @Produces(MediaType.APPLICATION_JSON) 
	   public Set<Seance> getAllSeanceWEB(){
		 return cinema.getAllSeance();
		
	 }
	 @POST
		@Path("/add")
		@Produces(MediaType.APPLICATION_JSON) 
		public void addFilmWEB(@WebParam Film film) {
		 Film f=cinema.createFilm(film.getNom());
		 cinema.ajouterFilm(f);
			
		}
	 @POST
		@Path("/update")
		@Produces(MediaType.APPLICATION_JSON) 
		public void updateFilmWEB(@WebParam Film f) {
		 Film film=cinema.findFilmById(f.getId());
		 film.setNom(f.getNom());
		 cinema.updateFilm(film);
			
		}
	 @GET
	 @Path("/tarif/{id}")
	 @Produces(MediaType.APPLICATION_JSON) 
	public float getTarifWEB(@PathParam(value="id") int id){
		 return cinema.getTarif(id);
		
	 }
	 @POST
		@Path("/init/{name}/{password}")
		@Produces(MediaType.APPLICATION_JSON) 
		public Response initWEB(@PathParam(value="name") String name,@PathParam(value="password") String password) {
		 try {
			utilisateur.init(name, password);
			return Response.ok("{\"message\": \"Connexion avec succées \"}").build();
		} catch (UserNotFoundException e) {
			 return Response.status(Response.Status.BAD_REQUEST)
                     .entity("{\"error\": \"" + e.getMessage() + "\"}")
                     .build();
		}
		
		
			
		}
	 @GET
	 @Path("/init/solde")
	 @Produces(MediaType.APPLICATION_JSON) 
	public float getSoldeWEB(){
		 return utilisateur.solde();
		
	 }
	 @GET
	 @Path("/init/name")
	 @Produces(MediaType.APPLICATION_JSON) 
	public String getNameWEB(){
		 return utilisateur.getName();
		
	 }
	 
	 @POST
		@Path("/init/reserv/{id}")
		@Produces(MediaType.APPLICATION_JSON) 
		public Response reservWEB(@PathParam(value="id")int id) {
		 Seance s=cinema.getSeanceById(id);
		 try {
			cinema.reserv(s, utilisateur);
			return Response.ok("{\"message\": \"Votre place est réservée \"}").build();
		} catch (SoldeNegatifException e) {
			return Response.status(Response.Status.BAD_REQUEST)
                    .entity("{\"error\": \"" + e.getMessage() + "\"}")
                    .build();
		} catch (PlusDePlaceException e) {
			return Response.status(Response.Status.BAD_REQUEST)
                    .entity("{\"error\": \"" + e.getMessage() + "\"}")
                    .build();
		}
	 }
		
	 
	
	 

}
