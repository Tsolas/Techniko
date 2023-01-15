package com.tsolas.technico.dto;

import com.tsolas.technico.enums.RepairStatus;
import com.tsolas.technico.enums.RepairType;
import com.tsolas.technico.model.Repair;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RepairDto {

  private int id;
  private RepairType repairType;
  private String repairDescription;
  private String submissionDate;
  private String workDescription;
  private String startDate;
  private String endDate;
  private double cost;
  private boolean acceptance;
  private RepairStatus repairStatus;
  private String actualStartDate;
  private String actualEndDate;

  public RepairDto(Repair repair) {
    this.id = repair.getId();
    this.repairType = repair.getRepairType();
    this.repairDescription = repair.getRepairDescription();
    this.submissionDate = repair.getSubmissionDate();
    this.workDescription = repair.getWorkDescription();
    this.startDate = repair.getStartDate();
    this.endDate = repair.getEndDate();
    this.cost = repair.getCost();
    this.repairStatus = repair.getRepairStatus();
    this.actualStartDate = repair.getActualStartDate();
    this.actualEndDate = repair.getActualEndDate();
    this.acceptance = repair.isAcceptance();
  }

  public Repair asRepair() {
    Repair repair = new Repair();
    repair.setId(id);
    repair.setRepairType(repairType);
    repair.setRepairDescription(repairDescription);
    repair.setSubmissionDate(submissionDate);
    repair.setWorkDescription(workDescription);
    repair.setStartDate(startDate);
    repair.setEndDate(endDate);
    repair.setCost(cost);
    repair.setRepairStatus(repairStatus);
    repair.setActualStartDate(actualStartDate);
    repair.setActualEndDate(actualEndDate);
    repair.setAcceptance(acceptance);
    return repair;
  }
}
