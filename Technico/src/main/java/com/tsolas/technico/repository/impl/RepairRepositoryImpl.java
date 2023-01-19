package com.tsolas.technico.repository.impl;

import com.tsolas.technico.enums.RepairStatus;
import com.tsolas.technico.model.Property;
import com.tsolas.technico.model.PropertyOwner;
import com.tsolas.technico.model.Repair;
import com.tsolas.technico.repository.RepairRepository;
import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

public class RepairRepositoryImpl extends RepositoryImpl<Repair> implements RepairRepository {

  @Override
  public Class<Repair> getClassType() {
    return Repair.class;
  }

  @Override
  public String getClassName() {
    return "repair";
  }

  @Override
  public List<Repair> findPending() {
    return em.createQuery("SELECT r FROM repair r WHERE r.repairStatus = :status", Repair.class)
            .setParameter("status", RepairStatus.PENDING).getResultList();
  }

  @Override
  public List<Repair> findbyExactDate(String date) {
    return em.createQuery("SELECT r FROM repair r WHERE r.submissionDate = :submissionDate", Repair.class)
            .setParameter("submissionDate", date).getResultList();
  }

  @Override
  @Transactional
  public List<Repair> findRepairsOfOwner(int id) {
    PropertyOwner propertyOwner = em.find(PropertyOwner.class, id);
    List<Property> properties = propertyOwner.getProperties();
    List<Repair> repairs = new ArrayList<>();
    for (Property property : properties) {
      repairs.addAll(property.getRepairs());
    }
    return repairs;
  }

  @Override
  public List<Repair> findOnGoing() {
    return em.createQuery("SELECT r FROM repair r WHERE r.repairStatus = :status", Repair.class)
            .setParameter("status", RepairStatus.IN_PROGRESS).getResultList();
  }

}
