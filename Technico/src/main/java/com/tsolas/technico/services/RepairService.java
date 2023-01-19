package com.tsolas.technico.services;

import com.tsolas.technico.dto.RepairDto;
import com.tsolas.technico.enums.RepairStatus;
import com.tsolas.technico.enums.RepairType;
import java.util.List;

public interface RepairService {

  RepairDto addNewRepair(int propertyId, RepairDto repairDto);

  boolean deleteRepair(int id);

  RepairDto getRepair(int id);

  List<RepairDto> getAllRepairs();

  List<RepairDto> getPendingRepairs();

  List<RepairDto> getOnGoingRepairs();

  RepairDto changeRepairType(int id, RepairType newRepairType);

  RepairDto changeRepairStatus(int id, RepairStatus newRepairStatus);

  RepairDto changeRepairDescription(int id, String newRepairDescription);

  RepairDto changeSubmissionDate(int id, String newSubmissionDate);

  RepairDto changeWorkDescription(int id, String newWorkDescription);

  RepairDto changeStartDate(int id, String newStartDate);

  RepairDto changeActualStartDate(int id, String newActualStartDate);

  RepairDto changeEndDate(int id, String newEndDate);

  RepairDto changeActualEndDate(int id, String newActualEndDate);

  RepairDto changeCost(int id, double newCost);

  RepairDto accept(int id);

  RepairDto decline(int id);

  List<RepairDto> getRepairsBySubmissionDate(String submissionDate);

  List<RepairDto> getRepairsOfOwner(int id);

}
