package com.tsolas.technico.resources;

import com.tsolas.technico.dto.PropertyOwnerDto;
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

@Path("ownerResource")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class OwnerResource {

  @Inject
  private OwnerService ownerService;

  @GET
  @Path("owner/{ownerId}")
  @Produces("application/json")
  public RestApiResult<PropertyOwnerDto> getOwner(@PathParam("ownerId") int ownerId) {
    return ownerService.getOwner(ownerId);

  }

  @GET
  @Path("owner/vat/{vat}")
  @Produces("application/json")
  public RestApiResult<PropertyOwnerDto> getOwnerByVat(@PathParam("vat") int vat) {
    return ownerService.getOwnerByVat(vat);

  }

  @GET
  @Path("owner/email/{email}")
  @Produces("application/json")
  public RestApiResult<PropertyOwnerDto> getOwnerByEmail(@PathParam("email") String email) {
    return ownerService.getOwnerByEmail(email);

  }

  @PUT
  @Path("owner/{ownerId}")
  @Consumes("application/json")
  public RestApiResult<PropertyOwnerDto> updateOwner(PropertyOwnerDto ownerDto, @PathParam("ownerId") int ownerId) {
    return ownerService.updateOwner(ownerDto, ownerId);
  }

  @POST
  @Path("owner")
  @Produces("application/json")
  @Consumes("application/json")
  public void createNewOwner(PropertyOwnerDto owner) {
    ownerService.createPropertyOwner(owner);

  }

  @DELETE
  @Path("owner/{ownerId}")
  @Produces("application/json")
  @Consumes("application/json")
  public boolean deleteOwner(@PathParam("ownerId") int ownerId) {
    return ownerService.deletePropertyOwner(ownerId);
  }
}
