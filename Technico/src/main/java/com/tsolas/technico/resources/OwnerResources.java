package com.tsolas.technico.resources;

import com.tsolas.technico.dto.PropertyOwnerDto;
import com.tsolas.technico.dto.RestApiResult;
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
import com.tsolas.technico.services.PropertyOwnerService;
import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.ws.rs.HeaderParam;
import java.util.List;

@Path("/ownersResource")
public class OwnerResources {

  @Inject
  private PropertyOwnerService ownerService;

  @POST
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  @Path("/addOwner")
  //@RolesAllowed({"ADMIN", "USER"})
  @PermitAll
  public RestApiResult<PropertyOwnerDto> insert(PropertyOwnerDto owner) {
    return ownerService.addNewOwner(owner);
  }

  @DELETE
  @Path("deleteOwner/{id}")
  @Produces("application/json")
  @Consumes("application/json")
  public boolean deletePropertyOwner(@PathParam("id") int id) {
    return ownerService.deletePropertyOwner(id);
  }

  @GET
  @Path("/owner/{id}")
  @RolesAllowed({"ADMIN", "USER"})
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  public PropertyOwnerDto getOneOwner(@PathParam("id") int id) {
    return ownerService.getOwner(id);
  }

  @GET
  @Path("/owners")
  @RolesAllowed({"ADMIN", "USER"})
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  public List<PropertyOwnerDto> readAll() {
    return ownerService.getAllOwners();
  }

  @GET
  @Path("/owner/vat/{vat}")
  @RolesAllowed({"ADMIN", "USER"})
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  public RestApiResult<PropertyOwnerDto> returnOwnerByVat(@PathParam("vat") int vat) {
    return ownerService.getOwnerByVat(vat);
  }

  @GET
  @Path("/owner/email/{email}")
  @RolesAllowed({"ADMIN", "USER"})
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  public RestApiResult<PropertyOwnerDto> returnOwnerByVat(@PathParam("email") String email) {
    return ownerService.getOwnerByEmail(email);
  }

  @PUT
  @Path("/updateAddress/ownerId/{id}")
  @RolesAllowed({"ADMIN", "USER"})
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.TEXT_PLAIN)
  public PropertyOwnerDto updateAddress(@PathParam("id") int id, String newAddress) {
    return ownerService.changeAddress(id, newAddress);
  }

  @PUT
  @Path("/updateEmail/ownerId/{id}")
  @RolesAllowed({"ADMIN", "USER"})
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.TEXT_PLAIN)
  public PropertyOwnerDto updateEmail(@PathParam("id") int id, String email) {
    return ownerService.changeEmail(id, email);
  }

  @PUT
  @Path("/updateName/ownerId/{id}")
  @RolesAllowed({"ADMIN", "USER"})
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.TEXT_PLAIN)
  public PropertyOwnerDto updateName(@PathParam("id") int id, String name) {
    return ownerService.changeName(id, name);
  }

  @PUT
  @Path("/updateSurname/ownerId/{id}")
  @RolesAllowed({"ADMIN", "USER"})
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.TEXT_PLAIN)
  public PropertyOwnerDto updateSurname(@PathParam("id") int id, String surname) {
    return ownerService.changeSurname(id, surname);
  }

  @PUT
  @Path("/updatePassword/ownerId/{id}")
  @RolesAllowed({"ADMIN", "USER"})
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.TEXT_PLAIN)
  public PropertyOwnerDto updatePassword(@PathParam("id") int id, String password) {
    return ownerService.changePassword(id, password);
  }

  @PUT
  @Path("/updatePhoneNumber/ownerId/{id}")
  @RolesAllowed({"ADMIN", "USER"})
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.TEXT_PLAIN)
  public PropertyOwnerDto updatePhoneNumber(@PathParam("id") int id, String phoneNumber) {
    return ownerService.changePhoneNumber(id, phoneNumber);
  }

  @PUT
  @Path("/updateUsername/ownerId/{id}")
  @RolesAllowed({"ADMIN", "USER"})
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.TEXT_PLAIN)
  public PropertyOwnerDto updateUsername(@PathParam("id") int id, String username) {
    return ownerService.changeUsername(id, username);
  }

  @PUT
  @Path("/updateVat/ownerId/{id}")
  @RolesAllowed({"ADMIN", "USER"})
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.TEXT_PLAIN)
  public PropertyOwnerDto updateVat(@PathParam("id") int id, int vat) {
    //int vat = Integer.parseInt(vatString);
    return ownerService.changeVat(id, vat);
  }

  @POST
  @Path("/login")
  @PermitAll
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  public PropertyOwnerDto loginUser(@HeaderParam("Authorization") String authorization) {
    return ownerService.getUser(authorization);
  }

}
