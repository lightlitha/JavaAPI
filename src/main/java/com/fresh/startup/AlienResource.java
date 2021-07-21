package com.fresh.startup;

import java.util.Arrays;
import java.util.List;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("aliens")
public class AlienResource {
	
	AlienRepository repo = new AlienRepository();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Alien> getAliens() {
		System.out.println("Calling getAlien function");
		
		return repo.getAliens();
	}
	
	@POST
	@Path("alien")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Alien createAlien(Alien a1) {
		System.out.println("Calling createAlien function");
		System.out.println(a1);
		repo.createAlien(a1);
		return a1;
	}
}
