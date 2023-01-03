package com.tsolas.technico.repository.impl;

import com.tsolas.technico.enums.RepairStatus;
import com.tsolas.technico.enums.RepairType;
import com.tsolas.technico.model.Property;
import com.tsolas.technico.model.Repair;
import com.tsolas.technico.repository.RepairRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.io.IOError;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.List;
import java.util.Properties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RepairRepositoryImpl extends RepositoryImpl<Repair> implements RepairRepository {

  private final Properties sqlCommands = new Properties();
  private static final Logger logger = LogManager.getLogger(RepairRepositoryImpl.class);

  {
    final ClassLoader loader = getClass().getClassLoader();
    try ( InputStream config = loader.getResourceAsStream("sql.properties")) {
      sqlCommands.load(config);
    } catch (IOException e) {
      throw new IOError(e);
    }
  }

  @PersistenceContext(unitName = "Persistence")
  private EntityManager entityManager;

  @Override
  public Repair search(int id) {
    return entityManager.find(Repair.class, id);
  }

  @Override
  public List<Repair> search(LocalDate submissionDate) {
    return entityManager.createQuery("select x from repair x where x.submissionDate =: submissionDate ", Repair.class)
            .setParameter("submissionDate", submissionDate).getResultList();
  }

  @Override
  public void updatePropertyId(int repairId, int propertyId) {
    Repair repair = entityManager.find(Repair.class, repairId);
    Property property = entityManager.find(Property.class, propertyId);
    try {
      repair.setProperty(property);
      entityManager.getTransaction().begin();
      entityManager.merge(repair);
      entityManager.getTransaction().commit();
      logger.info("The Repair's property id has been updated");
    } catch (Exception ex) {
      logger.warn(" The Repair's property id could not get updated", ex);
    }
  }

  @Override
  public void updateRepairType(int id, RepairType repairType) {
    Repair repair = entityManager.find(Repair.class, id);
    repair.setRepairType(repairType);
    try {
      entityManager.getTransaction().begin();
      entityManager.persist(repair);
      entityManager.getTransaction().commit();
      logger.info("The Repair Type has been updated");
    } catch (Exception ex) {
      logger.warn("The Repair Type could not be updated", ex);
    }
  }

  @Override
  public void updateRepairDescription(int id, String repairDescription) {
    Repair repair = entityManager.find(Repair.class, id);
    repair.setRepairDescription(repairDescription);
    try {
      entityManager.getTransaction().begin();
      entityManager.persist(repair);
      entityManager.getTransaction().commit();
      logger.info("The Repair's description has been updated");
    } catch (Exception ex) {
      logger.warn(" The Repair's description could not be updated", ex);
    }
  }

  @Override
  public void updateSubmissionDate(int id, LocalDate submissionDate) {
    Repair repair = entityManager.find(Repair.class, id);
    repair.setSubmissionDate(submissionDate);
    try {
      entityManager.getTransaction().begin();
      entityManager.persist(repair);
      entityManager.getTransaction().commit();
      logger.info("The Repair's submission date has been updated");
    } catch (Exception ex) {
      logger.warn(" The Repair's submission date could not be updated", ex);
    }
  }

  @Override
  public void updateWorkDescription(int id, String workDescription) {
    Repair repair = entityManager.find(Repair.class, id);
    repair.setWorkDescription(workDescription);
    try {
      entityManager.getTransaction().begin();
      entityManager.persist(repair);
      entityManager.getTransaction().commit();
      logger.info("The Repair's work description has been updated");
    } catch (Exception ex) {
      logger.warn("The Repair's work description could not be updated", ex);
    }
  }

  @Override
  public void updateStartDate(int id, LocalDate startDate) {
    Repair repair = entityManager.find(Repair.class, id);
    repair.setStartDate(startDate);
  }

  @Override
  public void updateEndDate(int id, LocalDate endDate) {
    Repair repair = entityManager.find(Repair.class, id);
    repair.setEndDate(endDate);
    try {
      entityManager.getTransaction().begin();
      entityManager.persist(repair);
      entityManager.getTransaction().commit();
      logger.info("The Repair's end date has been updated");
    } catch (Exception ex) {
      logger.warn(" The Repair's end date could nto be updated", ex);
    }
  }

  @Override
  public void updateCost(int id, double cost) {
    Repair repair = entityManager.find(Repair.class, id);
    repair.setCost(cost);
    try {
      entityManager.getTransaction().begin();
      entityManager.persist(repair);
      entityManager.getTransaction().commit();
      logger.info("The Repair's cost has been updated");
    } catch (Exception ex) {
      logger.warn("The Repair's cost could not be updated", ex);
    }
  }

  @Override
  public void updateAcceptance(int id, boolean acceptance) {
    Repair repair = entityManager.find(Repair.class, id);
    repair.setAcceptance(acceptance);
    try {
      entityManager.getTransaction().begin();
      entityManager.persist(repair);
      entityManager.getTransaction().commit();
      logger.info("The Repair's acceptance has been updated");
    } catch (Exception ex) {
      logger.warn("The Repair's acceptance could not be updated", ex);
    }
  }

  @Override
  public void updateRepairStatus(int id, RepairStatus repairStatus) {
    Repair repair = entityManager.find(Repair.class, id);
    repair.setRepairStatus(repairStatus);
    try {
      entityManager.getTransaction().begin();
      entityManager.persist(repair);
      entityManager.getTransaction().commit();
      logger.info("The Repair's status has been updated");
    } catch (Exception ex) {
      logger.warn("The Repair's status could not be updated", ex);
    }
  }

  @Override
  public void updateActualStartDate(int id, LocalDate actualStartDate) {
    Repair repair = entityManager.find(Repair.class, id);
    repair.setActualStartDate(actualStartDate);
    try {
      entityManager.getTransaction().begin();
      entityManager.persist(repair);
      entityManager.getTransaction().commit();
      logger.info("The Repair's actual start date has been updated");
    } catch (Exception ex) {
      logger.warn("The Repair's actual start date could not be updated", ex);
    }
  }

  @Override
  public void updateActualEndDate(int id, LocalDate actualEndDate) {
    Repair repair = entityManager.find(Repair.class, id);
    repair.setActualEndDate(actualEndDate);
    try {
      entityManager.getTransaction().begin();
      entityManager.persist(repair);
      entityManager.getTransaction().commit();
      logger.info("The Repair's actual end date has been updated");
    } catch (Exception ex) {
      logger.warn("The Repair's actual end could not be updated", ex);
    }
  }

  @Override
  public boolean delete(int id) {
    Repair repair = entityManager.find(Repair.class, id);
    if (repair == null) {
      return false;
    }
    try {
      entityManager.getTransaction().begin();
      entityManager.remove(repair);
      entityManager.getTransaction().commit();
    } catch (Exception ex) {
      logger.warn("Repair could not be deleted", ex);
    }
    return true;
  }

  @Override
  public List<Repair> readAll() {
    List<Repair> results = entityManager.createQuery(sqlCommands.getProperty("select.repairs"))
            .getResultList();
    return results;
  }

}
