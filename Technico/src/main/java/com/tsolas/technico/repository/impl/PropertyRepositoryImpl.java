package com.tsolas.technico.repository.impl;

import com.tsolas.technico.enums.PropertyType;
import com.tsolas.technico.model.Property;
import com.tsolas.technico.model.PropertyOwner;
import com.tsolas.technico.model.Repair;
import com.tsolas.technico.repository.PropertyRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import java.io.IOError;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PropertyRepositoryImpl extends RepositoryImpl<Property> implements PropertyRepository {

  private static final Logger logger = LogManager.getLogger(PropertyRepositoryImpl.class);
  private final Properties sqlCommands = new Properties();

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
  @Transactional
  public Property search(int id) {
    return entityManager.find(Property.class, id);
  }

  @Override
  public List<Property> searchByVat(int vat) {
    List<PropertyOwner> propertyOwners = entityManager.createQuery(sqlCommands.getProperty("select.owner.byVat"), PropertyOwner.class)
            .setParameter("vat", vat).getResultList();
    return propertyOwners.get(0).getProperties();
  }

  @Override
  public void updateOwnerVat(int propertyId, int vat) {
    Property property = entityManager.find(Property.class, propertyId);
    try {
      PropertyOwner propertyOwner = property.getOwner();
      propertyOwner.setVat(vat);
      entityManager.getTransaction().begin();
      entityManager.persist(propertyOwner);
      entityManager.getTransaction().commit();
    } catch (Exception e) {
      logger.warn("The owner's Vat could not get updated", e);
    }
  }

  @Override
  public void updateAddress(int id, String address) {
    Property property = entityManager.find(Property.class, id);
    try {
      property.setAddress(address);
      entityManager.getTransaction().begin();
      entityManager.persist(property);
      entityManager.getTransaction().commit();
    } catch (Exception e) {
      logger.warn("The address could not get updated", e);
    }
  }

  @Override
  public void updateYearOfConstruction(int id, String year) {
    Property property = entityManager.find(Property.class, id);
    try {
      property.setYearOfConstruction(year);
      entityManager.getTransaction().begin();
      entityManager.persist(property);
      entityManager.getTransaction().commit();
      logger.info("The year of construction has been updated");
    } catch (Exception e) {
      logger.warn("The year of construction could not get updated ", e);
    }
  }

  @Override
  public void updatePropertyType(int id, PropertyType propertyType) {
    Property property = entityManager.find(Property.class, id);
    try {
      property.setPropertyType(propertyType);
      entityManager.getTransaction().begin();
      entityManager.persist(property);
      entityManager.getTransaction().commit();
      logger.info("The property type has been updated");
    } catch (Exception e) {
      logger.warn(" The property type could not get updated", e);
    }
  }

  @Override
  public boolean delete(int id) {
    List<Repair> repairList = entityManager.createQuery("select r from repair r where r.property.id=:propertyId", Repair.class)
            .setParameter("propertyId", id).getResultList();
    try {
      for (Repair repair : repairList) {
        entityManager.getTransaction().begin();
        entityManager.find(Property.class, repair.getId());
        entityManager.remove(repair);
        entityManager.getTransaction().commit();
      }
      Property property = entityManager.find(Property.class, id);
      entityManager.getTransaction().begin();
      entityManager.remove(property);
      entityManager.getTransaction().commit();
      logger.info("Deletion was successful");
    } catch (Exception e) {
      logger.warn("Deletion failed", e);
    }
    return true;
  }

  @Override
  public void updateOwnerId(int propertyId, int propertyOwnerId) {
    Property property = entityManager.find(Property.class, propertyId);
    PropertyOwner propertyOwner = entityManager.find(PropertyOwner.class, propertyOwnerId);
    try {
      property.setOwner(propertyOwner);
      entityManager.getTransaction().begin();
      entityManager.merge(property);
      entityManager.getTransaction().commit();
      logger.info("Owner's id has been updated");
    } catch (Exception e) {
      logger.warn("Owner's id could not get updated", e);
    }
  }

  @Override
  @Transactional
  public List<Property> readAll() {
    List<Property> results = entityManager.createQuery(sqlCommands.getProperty("select.properties"))
            .getResultList();
    return results;
  }

  @Override
  public boolean deleteProperty(int id) {
    Property property = entityManager.find(Property.class, id);
    if (property == null) {
      return false;
    }
    entityManager.remove(property);
    return true;
  }

  @Override
  @Transactional
  public Property findById(int id) {
    Property property = entityManager.find(Property.class, id);
    return property;
  }
}
