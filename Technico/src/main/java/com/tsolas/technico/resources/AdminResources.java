package com.tsolas.technico.resources;

import com.tsolas.technico.dto.RepairDto;
import com.tsolas.technico.dto.RestApiResult;
import com.tsolas.technico.model.Repair;
import com.tsolas.technico.services.AdminService;
import com.tsolas.technico.services.OwnerService;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import java.util.List;
import java.util.stream.Collectors;

@Path("admin")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AdminResources {

  @Inject
  private AdminService adminService;

  @Inject
  private OwnerService ownerService;

  @GET
  @Path("pendingRepairs")
  @Produces("application/json")
  public List<RepairDto> getPendingRepairs() {
    List<Repair> repairs = adminService.getPendingRepairs();
    return repairs.stream().map(RepairDto::new).collect(Collectors.toList());
  }

  @GET
  @Path("datesPendingRepairs")
  @Produces("application/json")
  public List<Repair> getActualDatesOfPendingRepairs() {
    List<Repair> repairs = adminService.getActualDatesOfPendingRepairs();
    return repairs;

  }

  @PUT
  @Path("proposeCostsAndDates/{repairId}")
  @Consumes("application/json")
  public RestApiResult<RepairDto> proposeCostsAndDates(RepairDto repairDto, @PathParam("repairId") int repairId) {
    return ownerService.updateRepair(repairDto, repairId);
  }

}
