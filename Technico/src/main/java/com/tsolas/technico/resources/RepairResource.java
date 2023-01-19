package com.tsolas.technico.resources;

import com.tsolas.technico.dto.RepairDto;
import com.tsolas.technico.enums.RepairStatus;
import com.tsolas.technico.enums.RepairType;
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
import com.tsolas.technico.services.RepairService;
import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import java.util.List;

@Path("/repairResource")
public class RepairResource {

  @Inject
  private RepairService repairService;

  @POST
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  @Path("/addRepair/propertyId/{propertyId}")
  //@RolesAllowed({"ADMIN", "USER"})
  @PermitAll
  public RepairDto insertRepair(RepairDto repair, @PathParam("propertyId") int propertyId) {
    return repairService.addNewRepair(propertyId, repair);
  }

  @DELETE
  @Path("deleteRepair/{id}")
  @Produces("application/json")
  @Consumes("application/json")
  @RolesAllowed({"ADMIN", "USER"})
  public boolean deleteRepair(@PathParam("id") int id) {
    return repairService.deleteRepair(id);
  }

  @GET
  @Path("/repair/{id}")
  @RolesAllowed({"ADMIN", "USER"})
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  public RepairDto getOneRepair(@PathParam("id") int id) {
    return repairService.getRepair(id);
  }

  @GET
  @Path("/repairs")
  @RolesAllowed({"ADMIN", "USER"})
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  public List<RepairDto> readAllRepairs() {
    return repairService.getAllRepairs();
  }

  @GET
  @Path("/pendingRepairs")
  @RolesAllowed({"ADMIN", "USER"})
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  public List<RepairDto> readAllPendingRepairs() {
    return repairService.getPendingRepairs();
  }

  @GET
  @Path("/ongoingRepairs")
  @RolesAllowed({"ADMIN", "USER"})
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  public List<RepairDto> readAllOnGoingRepairs() {
    return repairService.getOnGoingRepairs();
  }

  @PUT
  @Path("/updateRepairType/repairId/{id}")
  @RolesAllowed({"ADMIN", "USER"})
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.TEXT_PLAIN)
  public RepairDto updateRepairType(@PathParam("id") int id, String newRepairType) {
    RepairType repairType = RepairType.valueOf(newRepairType.toUpperCase());
    return repairService.changeRepairType(id, repairType);
  }

  @PUT
  @Path("/updateRepairStatus/repairId/{id}")
  @RolesAllowed({"ADMIN", "USER"})
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.TEXT_PLAIN)
  public RepairDto updateRepairStatus(@PathParam("id") int id, String newRepairStatus) {
    RepairStatus repairStatus = RepairStatus.valueOf(newRepairStatus.toUpperCase());
    return repairService.changeRepairStatus(id, repairStatus);
  }

  @PUT
  @Path("/updateRepairDescription/repairId/{id}")
  @RolesAllowed({"ADMIN", "USER"})
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.TEXT_PLAIN)
  public RepairDto updateRepairDescription(@PathParam("id") int id, String newRepairDescription) {
    return repairService.changeRepairDescription(id, newRepairDescription);
  }

  @PUT
  @Path("/updateSubmissionDate/repairId/{id}")
  @RolesAllowed({"ADMIN", "USER"})
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.TEXT_PLAIN)
  public RepairDto updateSubmissionDate(@PathParam("id") int id, String newSubmissionDate) {
    return repairService.changeRepairDescription(id, newSubmissionDate);
  }

  @PUT
  @Path("/updateWorkDescription/repairId/{id}")
  @RolesAllowed({"ADMIN", "USER"})
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.TEXT_PLAIN)
  public RepairDto updateWorkDescription(@PathParam("id") int id, String newWorkDescription) {
    return repairService.changeWorkDescription(id, newWorkDescription);
  }

  @PUT
  @Path("/updateStartDate/repairId/{id}")
  @RolesAllowed({"ADMIN", "USER"})
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.TEXT_PLAIN)
  public RepairDto updateStartDate(@PathParam("id") int id, String newStartDate) {
    return repairService.changeStartDate(id, newStartDate);
  }

  @PUT
  @Path("/updateEndDate/repairId/{id}")
  @RolesAllowed({"ADMIN", "USER"})
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.TEXT_PLAIN)
  public RepairDto updateEndDate(@PathParam("id") int id, String newEndDate) {
    return repairService.changeEndDate(id, newEndDate);
  }

  @PUT
  @Path("/proposeCost/repairId/{id}")
  @RolesAllowed({"ADMIN", "USER"})
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.TEXT_PLAIN)
  public RepairDto proposeCost(@PathParam("id") int id, double newCost) {
    return repairService.changeCost(id, newCost);
  }

  @PUT
  @Path("/acceptRepair/repairId/{id}")
  @RolesAllowed({"ADMIN", "USER"})
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.TEXT_PLAIN)
  public RepairDto acceptRepair(@PathParam("id") int id) {
    return repairService.accept(id);
  }

  @PUT
  @Path("/declineRepair/repairId/{id}")
  @RolesAllowed({"ADMIN", "USER"})
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.TEXT_PLAIN)
  public RepairDto declineRepair(@PathParam("id") int id) {
    return repairService.decline(id);
  }

  @PUT
  @Path("/updateActualStartDate/repairId/{id}")
  @RolesAllowed({"ADMIN", "USER"})
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.TEXT_PLAIN)
  public RepairDto proposeStartDate(@PathParam("id") int id, String newActualStartDate) {
    return repairService.changeActualStartDate(id, newActualStartDate);
  }

  @PUT
  @Path("/updateActualEndDate/repairId/{id}")
  @RolesAllowed({"ADMIN", "USER"})
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.TEXT_PLAIN)
  public RepairDto proposeEndDate(@PathParam("id") int id, String newActualEndDate) {
    return repairService.changeActualEndDate(id, newActualEndDate);
  }

  @GET
  @Path("/repairs/submissionDate/{submissionDate}")
  @RolesAllowed({"ADMIN", "USER"})
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  public List<RepairDto> readRepairsBySubmissionDate(@PathParam("submissionDate") String submissionDate) {
    return repairService.getRepairsBySubmissionDate(submissionDate);
  }

  @GET
  @Path("/repairs/owner/{id}")
  @RolesAllowed({"ADMIN", "USER"})
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  public List<RepairDto> readRepairsOfOwner(@PathParam("id") int id) {
    return repairService.getRepairsOfOwner(id);
  }

}
