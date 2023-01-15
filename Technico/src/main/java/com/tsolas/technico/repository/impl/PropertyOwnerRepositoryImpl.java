package com.tsolas.technico.repository.impl;

import com.tsolas.technico.model.PropertyOwner;
import com.tsolas.technico.repository.PropertyOwnerRepository;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import java.util.List;

public class PropertyOwnerRepositoryImpl extends RepositoryImpl<PropertyOwner> implements PropertyOwnerRepository {

  @Override
  public Class<PropertyOwner> getClassType() {
    return PropertyOwner.class;
  }

  @Override
  public String getClassName() {
    return "propertyowner";
  }

  @Override
  public PropertyOwner findbyVat(int vat) {
    return em.createQuery("SELECT p from propertyowner p where p.vat =:vat", PropertyOwner.class)
            .setParameter("vat", vat).getSingleResult();
  }

  @Override
  public PropertyOwner findbyEmail(String email) {
    return em.createQuery("SELECT p from propertyowner p where p.email =:email", PropertyOwner.class)
            .setParameter("email", email).getSingleResult();
  }

  @Override
  @Transactional
  public boolean delete(int id) {
    PropertyOwner propertyOwner = read(id);
    Query query = em.createQuery("UPDATE property p SET p.owner = null WHERE p.owner = :propertyOwner");
    query.setParameter("propertyOwner", propertyOwner);
    query.executeUpdate();
    em.remove(propertyOwner);
    if (propertyOwner == null) {
      return false;
    }
    em.remove(propertyOwner);
    return true;
  }

  @Override
  public List<PropertyOwner> findEmails(String email) {
    return em.createQuery("SELECT p FROM propertyowner p WHERE p.email = :email", PropertyOwner.class)
            .setParameter("email", email)
            .getResultList();
  }

  @Override
  public List<PropertyOwner> findVats(int vat) {
    return em.createQuery("SELECT p FROM propertyowner p WHERE p.vat = :vat", PropertyOwner.class)
            .setParameter("vat", vat)
            .getResultList();
  }

  @Override
  public List<PropertyOwner> findUsernames(String username) {
    return em.createQuery("SELECT p FROM propertyowner p WHERE p.username = :username", PropertyOwner.class)
            .setParameter("username", username)
            .getResultList();
  }

  @Override
  public String checkRole(String username, String password) {
    try {
      return em.createQuery("SELECT p.role from propertyowner p where username=:u1 and password=:u2")
              .setParameter("u1", username)
              .setParameter("u2", password)
              .getSingleResult()
              .toString();
    } catch (Exception e) {
      return "";
    }
  }

}
