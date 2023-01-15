package com.tsolas.technico.resources;

import com.tsolas.technico.dto.PropertyDto;
import com.tsolas.technico.enums.PropertyType;
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
import com.tsolas.technico.services.PropertyService;
import jakarta.annotation.security.RolesAllowed;
import java.util.List;

@Path("/propertyResource")
public class PropertyResource {

  @Inject
  private PropertyService propertyService;

  @POST
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  @Path("/addProperty/ownerId/{ownerId}")
  @RolesAllowed({"ADMIN", "USER"})
  public PropertyDto insertProperty(PropertyDto property, @PathParam("ownerId") int ownerId) {
    return propertyService.addNewProperty(ownerId, property);
  }

  @DELETE
  @Path("deleteProperty/{id}")
  @Produces("application/json")
  @Consumes("application/json")
  @RolesAllowed({"ADMIN", "USER"})
  public boolean deleteProperty(@PathParam("id") int id) {
    return propertyService.deleteProperty(id);
  }

  @GET
  @Path("/property/{id}")
  @RolesAllowed({"ADMIN", "USER"})
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  public PropertyDto getOneProperty(@PathParam("id") int id) {
    return propertyService.getProperty(id);
  }

  @GET
  @Path("/properties")
  @RolesAllowed({"ADMIN", "USER"})
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  public List<PropertyDto> readAllProperties() {
    return propertyService.getAllProperties();
  }

  @GET
  @Path("/property/e9/{e9}")
  @RolesAllowed({"ADMIN", "USER"})
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  public PropertyDto returnOwnerByVat(@PathParam("e9") int e9) {
    return propertyService.getPropertyByE9(e9);
  }

  @GET
  @Path("/properties/ownerVat/{ownerVat}")
  @RolesAllowed({"ADMIN", "USER"})
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  public List<PropertyDto> returnPropertiesByOwnervat(@PathParam("ownerVat") int ownerVat) {
    return propertyService.getPropertiesByOwnerVat(ownerVat);
  }

  @PUT
  @Path("/updateAddress/propertyId/{id}")
  @RolesAllowed({"ADMIN", "USER"})
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.TEXT_PLAIN)
  public PropertyDto updateAddress(@PathParam("id") int id, String newAddress) {
    return propertyService.changeAddress(id, newAddress);
  }

  @PUT
  @Path("/updateE9/propertyId/{id}")
  @RolesAllowed({"ADMIN", "USER"})
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.TEXT_PLAIN)
  public PropertyDto updateAddress(@PathParam("id") int id, int newE9) {
    return propertyService.changeE9(id, newE9);
  }

  @PUT
  @Path("/updateYearOfConstruction/propertyId/{id}")
  @RolesAllowed({"ADMIN", "USER"})
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.TEXT_PLAIN)
  public PropertyDto updateYear(@PathParam("id") int id, String newYear) {
    return propertyService.changeYear(id, newYear);
  }

  @PUT
  @Path("/updatepropertyType/propertyId/{id}")
  @RolesAllowed({"ADMIN", "USER"})
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.TEXT_PLAIN)
  public PropertyDto updateYear(@PathParam("id") int id, PropertyType newPropertyType) {
    return propertyService.changePropertyType(id, newPropertyType);
  }
}
