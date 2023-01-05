package com.tsolas.technico.resources;

import com.tsolas.technico.dto.PropertyDto;
import com.tsolas.technico.dto.RestApiResult;
import com.tsolas.technico.services.OwnerService;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("propertyResource")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PropertyResource {

  @Inject
  private OwnerService ownerService;

  @GET
  @Path("/property/{propertyId}")
  @Produces("application/json")
  public RestApiResult<PropertyDto> getProperty(@PathParam("propertyId") int propertyId) {
    return ownerService.getProperty(propertyId);
  }

  @PUT
  @Path("/property/{propertyId}")
  @Consumes("application/json")
  public RestApiResult<PropertyDto> updateProperty(PropertyDto propertyDto, @PathParam("propertyId") int propertyId) {
    return ownerService.updateProperty(propertyDto, propertyId);
  }

  @POST
  @Path("property")
  @Produces("application/json")
  @Consumes("application/json")
  public void createNewProperty(PropertyDto property) {
    ownerService.registerNewPropertyDto(property);
  }

  @DELETE
  @Path("property/{id}")
  @Consumes("application/json")
  public boolean deleteProperty(@PathParam("id") int id) {
    return ownerService.deleteProperty(id);
  }
}
