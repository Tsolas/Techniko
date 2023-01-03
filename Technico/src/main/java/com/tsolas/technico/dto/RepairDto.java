package com.tsolas.technico.dto;

import com.tsolas.technico.enums.RepairStatus;
import com.tsolas.technico.enums.RepairType;
import com.tsolas.technico.model.Repair;
import java.time.LocalDate;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RepairDto {

  private RepairType repairType;
  private String repairDescription;
  private LocalDate submissionDate;
  private String workDescription;
  private LocalDate startDate;
  private LocalDate endDate;
  private double cost;
  private boolean acceptance;
  private RepairStatus repairStatus;
  private LocalDate actualStartDate;
  private LocalDate actualEndDate;
  private PropertyDto property;

  public RepairDto(Repair repair) {
    if (repair != null) {
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
    }
  }

  public Repair asRepair() {
    Repair repair = new Repair();
    repair.setRepairType(repairType);
    repair.setRepairDescription(repairDescription);
    repair.setSubmissionDate(submissionDate);
    repair.setWorkDescription(workDescription);
    repair.setStartDate(startDate);
    repair.setEndDate(endDate);
    repair.setCost(cost);
    repair.setRepairStatus(repairStatus);
    repair.setActualStartDate(actualStartDate);
    repair.setEndDate(endDate);
    return repair;
  }
}
