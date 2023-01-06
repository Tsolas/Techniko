package com.tsolas.technico.services.implementation;

import com.tsolas.technico.model.Repair;
import com.tsolas.technico.repository.RepairRepository;
import com.tsolas.technico.services.AdminService;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.Tuple;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import java.io.IOError;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AdminServiceImpl implements AdminService {

  private static final Logger logger = LogManager.getLogger(AdminServiceImpl.class);
  @Inject
  private RepairRepository repairRepository;

  @PersistenceContext
  private EntityManager entityManager;

  private final Properties sqlCommands = new Properties();

  {
    final ClassLoader loader = getClass().getClassLoader();
    try ( InputStream config = loader.getResourceAsStream("sql.properties")) {
      sqlCommands.load(config);
    } catch (IOException e) {
      throw new IOError(e);
    }
  }

  @Override
  public void proposeCosts(Repair repair, double cost) {
    try {
      repairRepository.updateCost(repair.getId(), cost);
      logger.info("The cost of the repair has been proposed", repair.getId());
    } catch (Exception e) {
      logger.warn("Can not propose the costs");
    }
  }

  @Override
  public void proposeDates(Repair repair, String startDate, String endDate) {
    try {
      repairRepository.updateStartDate(repair.getId(), startDate);
      repairRepository.updateEndDate(repair.getId(), endDate);
      logger.info("The dates of the repair with id {} have been proposed", repair.getId());
    } catch (Exception e) {
      logger.warn("Can not propose the dates");
    }
  }

  @Override
  @Transactional
  public List<Repair> getActualDatesOfPendingRepairs() {
    Query query = entityManager.createQuery("SELECT r.id,r.actualStartDate,r.actualEndDate FROM repair r WHERE r.repairStatus = 'PENDING'");
    return query.getResultList();
  }

  @Override
  @Transactional
  public List<Repair> getPendingRepairs() {
    Query query = entityManager.createQuery("SELECT r FROM repair r WHERE r.repairStatus = 'PENDING'");
    return query.getResultList();
  }
}
