package com.tsolas.technico.services.implementation;

import com.tsolas.technico.dto.RepairDto;
import com.tsolas.technico.enums.RepairStatus;
import com.tsolas.technico.enums.RepairType;
import com.tsolas.technico.model.Property;
import com.tsolas.technico.model.Repair;
import com.tsolas.technico.repository.PropertyRepository;
import com.tsolas.technico.repository.RepairRepository;
import com.tsolas.technico.services.RepairService;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RepairServiceImpl implements RepairService {

  private static final Logger logger = LogManager.getLogger(RepairServiceImpl.class);

  @Inject
  private RepairRepository repairRepository;
  @Inject
  private PropertyRepository propertyRepository;

  @Override
  @Transactional
  public RepairDto addNewRepair(int propertyId, RepairDto repairDto) {
    logger.trace("Trying to add a new repair for property with id :" + propertyId);
    try {
      RepairType.valueOf(repairDto.getRepairType().toString());
    } catch (IllegalArgumentException e) {
      logger.error("Invalid RepairType value: " + repairDto.getRepairType());
      throw new IllegalArgumentException("Invalid RepairType value: " + repairDto.getRepairType());
    }
    try {
      RepairStatus.valueOf(repairDto.getRepairStatus().toString());
    } catch (IllegalArgumentException e) {
      logger.warn("Invalid RepairStatus value: " + repairDto.getRepairStatus());
      throw new IllegalArgumentException("Invalid RepairStatus value: " + repairDto.getRepairStatus());
    }
    Repair repair = repairDto.asRepair();
    Property property = propertyRepository.read(propertyId);
    repair.setProperty(property);
    property.getRepairs().add(repair);
    propertyRepository.create(property);
    logger.info("Adding new repair: " + repairDto.toString());
    repairRepository.create(repair);
    return new RepairDto(repair);
  }

  @Override
  public boolean deleteRepair(int id) {
    boolean deleted = repairRepository.delete(id);
    if (deleted) {
      logger.info("Deleting repair with id: " + id);
    } else {
      logger.warn("Deletion of repair with id: " + id + "failed");
    }
    return deleted;
  }

  @Override
  public RepairDto getRepair(int id) {
    logger.trace("Trying to find repair with id: " + id);
    try {
      RepairDto repair = new RepairDto(repairRepository.read(id));
      logger.info("Returning repair: " + repair.toString());
      return repair;
    } catch (NullPointerException e) {
      logger.error("Error getting repair with id " + id, e);
      return null;
    }
  }

  @Override
  public List<RepairDto> getAllRepairs() {
    logger.info("Returning all repairs");
    return repairRepository.readAll().stream().map(RepairDto::new).collect(Collectors.toList());
  }

  @Override
  public List<RepairDto> getPendingRepairs() {
    logger.info("Getting all pending repairs");
    return repairRepository.findPending().stream().map(RepairDto::new).collect(Collectors.toList());
  }

  @Override
  public RepairDto changeRepairType(int id, RepairType newRepairType) {
    Repair repair = repairRepository.read(id);
    if (repair == null) {
      logger.error("Repair with id :" + id + "doesn't exist.");
      return null;
    }
    try {
      RepairType.valueOf(repair.getRepairType().toString());
    } catch (IllegalArgumentException e) {
      logger.warn("Invalid RepairType value: " + repair.getRepairType());
      throw new IllegalArgumentException("Invalid RepairType value: " + repair.getRepairType());
    }
    repair.setRepairType(newRepairType);
    logger.info("Changing repair type of repair with id: " + id + "to :" + newRepairType);
    repairRepository.create(repair);
    return new RepairDto(repair);
  }

  @Override
  public RepairDto changeRepairDescription(int id, String newRepairDescription) {
    Repair repair = repairRepository.read(id);
    if (repair == null) {
      logger.error("Repair with id :" + id + "doesn't exist.");
      return null;
    }
    repair.setRepairDescription(newRepairDescription);
    logger.info("Changing repair description of repair with id: " + id + "to :" + newRepairDescription);
    repairRepository.create(repair);
    return new RepairDto(repair);
  }

  @Override
  public RepairDto changeSubmissionDate(int id, String newSubmissionDate) {
    Repair repair = repairRepository.read(id);
    if (repair == null) {
      logger.error("Repair with id :" + id + "doesn't exist.");
      return null;
    }
    repair.setSubmissionDate(newSubmissionDate);
    logger.info("Changing submission date of repair with id: " + id + "to :" + newSubmissionDate);
    repairRepository.create(repair);
    return new RepairDto(repair);
  }

  @Override
  public RepairDto changeWorkDescription(int id, String newWorkDescription) {
    Repair repair = repairRepository.read(id);
    if (repair == null) {
      logger.error("Repair with id :" + id + "doesn't exist.");
      return null;
    }
    repair.setWorkDescription(newWorkDescription);
    logger.info("Changing work description of repair with id: " + id + "to :" + newWorkDescription);
    repairRepository.create(repair);
    return new RepairDto(repair);
  }

  @Override
  public RepairDto changeStartDate(int id, String newStartDate) {
    Repair repair = repairRepository.read(id);
    if (repair == null) {
      logger.error("Repair with id :" + id + "doesn't exist.");
      return null;
    }
    repair.setStartDate(newStartDate);
    logger.info("Changing start date of repair with id: " + id + "to :" + newStartDate);
    repairRepository.create(repair);
    return new RepairDto(repair);
  }

  @Override
  public RepairDto changeEndDate(int id, String newEndDate) {
    Repair repair = repairRepository.read(id);
    if (repair == null) {
      logger.error("Repair with id :" + id + "doesn't exist.");
      return null;
    }
    repair.setEndDate(newEndDate);
    logger.info("Changing end date of repair with id: " + id + "to :" + newEndDate);
    repairRepository.create(repair);
    return new RepairDto(repair);
  }

  @Override
  public RepairDto changeCost(int id, double newCost) {
    Repair repair = repairRepository.read(id);
    if (repair == null) {
      logger.error("Repair with id :" + id + "doesn't exist.");
      return null;
    }
    repair.setCost(newCost);
    logger.info("Changing cost of repair with id: " + id + "to :" + newCost);
    repairRepository.create(repair);
    return new RepairDto(repair);
  }

  @Override
  public RepairDto accept(int id) {
    Repair repair = repairRepository.read(id);
    if (repair == null) {
      logger.error("Repair with id :" + id + "doesn't exist.");
      return null;
    }
    repair.setAcceptance(true);
    logger.info("Accepting repair with id :" + id);
    repairRepository.create(repair);
    return new RepairDto(repair);
  }

  @Override
  public RepairDto decline(int id) {
    Repair repair = repairRepository.read(id);
    if (repair == null) {
      logger.error("Repair with id :" + id + "doesn't exist.");
      return null;
    }
    repair.setAcceptance(false);
    logger.info("Declining repair with id :" + id);
    repairRepository.create(repair);
    return new RepairDto(repair);
  }

  @Override
  public RepairDto changeRepairStatus(int id, RepairStatus newRepairStatus) {
    Repair repair = repairRepository.read(id);
    if (repair == null) {
      logger.error("Repair with id :" + id + "doesn't exist.");
      return null;
    }
    try {
      RepairStatus.valueOf(repair.getRepairStatus().toString());
    } catch (IllegalArgumentException e) {
      logger.warn("Invalid RepairStatus value: " + repair.getRepairStatus());
      throw new IllegalArgumentException("Invalid RepairStatus value: " + repair.getRepairStatus());
    }
    repair.setRepairStatus(newRepairStatus);
    logger.info("Changing status of repair with id: " + id + "to :" + newRepairStatus);
    repairRepository.create(repair);
    return new RepairDto(repair);
  }

  @Override
  public RepairDto changeActualStartDate(int id, String newActualStartDate) {
    Repair repair = repairRepository.read(id);
    if (repair == null) {
      logger.error("Repair with id :" + id + "doesn't exist.");
      return null;
    }
    repair.setActualStartDate(newActualStartDate);
    logger.info("Changing actual start date of repair with id: " + id + "to :" + newActualStartDate);
    repairRepository.create(repair);
    return new RepairDto(repair);
  }

  @Override
  public RepairDto changeActualEndDate(int id, String newActualEndDate) {
    Repair repair = repairRepository.read(id);
    if (repair == null) {
      logger.error("Repair with id :" + id + "doesn't exist.");
      return null;
    }
    repair.setActualEndDate(newActualEndDate);
    logger.info("Changing actual end date of repair with id: " + id + "to :" + newActualEndDate);
    repairRepository.create(repair);
    return new RepairDto(repair);
  }

  @Override
  public List<RepairDto> getRepairsBySubmissionDate(String submissionDate) {
    logger.info("Returning all repairs with submission date: " + submissionDate);
    return repairRepository.findbyExactDate(submissionDate).stream().map(RepairDto::new).collect(Collectors.toList());
  }

  @Override
  public List<RepairDto> getRepairsOfOwner(int id) {
    logger.info("Returning all repairs of owner with id : " + id);
    return repairRepository.findRepairsOfOwner(id).stream().map(RepairDto::new).collect(Collectors.toList());
  }

  @Override
  public List<RepairDto> getOnGoingRepairs() {
    logger.info("Getting all pending repairs");
    return repairRepository.findOnGoing().stream().map(RepairDto::new).collect(Collectors.toList());
  }

}
