package com.tsolas.technico.resources;

import com.tsolas.technico.services.IoServices;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/populate")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class DbDataPopulationResource {

  @Inject
  private IoServices ioServices;

  @GET
  public void populateDatabase() {
    ioServices.readOwnersCsv("‪C:\\Users\\Giorgos\\Documents\\owners.csv");
    ioServices.readPropertyCsv("‪C:\\Users\\Giorgos\\Documents\\property.csv");
    ioServices.readRepairCsv("‪C:\\Users\\Giorgos\\Documents\\repairs.csv");
    ioServices.relationshipsBetweenObjects();
  }
}
