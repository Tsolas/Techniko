package com.tsolas.technico.resources;

import com.tsolas.technico.dto.RepairDto;
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

@Path("repairResource")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class RepairResource {

  @Inject
  private OwnerService ownerService;

  @GET
  @Path("repair/{repairId}")
  @Produces("application/json")
  public RestApiResult<RepairDto> getRepair(@PathParam("repairId") int repairId) {
    return ownerService.getRepair(repairId);
  }

  @PUT
  @Path("repair/{repairId}")
  @Consumes("application/json")
  public RestApiResult<RepairDto> updateRepair(RepairDto repairDto, @PathParam("repairId") int repairId) {
    return ownerService.updateRepair(repairDto, repairId);
  }

  @POST
  @Path("/repair")
  @Produces("application/json")
  @Consumes("application/json")
  public void createNewRepair(RepairDto repair) {
    ownerService.createRepair(repair);
  }

  @DELETE
  @Path("repair/{repairId}")
  @Consumes("application/json")
  public boolean deleteRepair(@PathParam("repairId") int repairId) {
    return ownerService.deleteRepair(repairId);
  }
}
