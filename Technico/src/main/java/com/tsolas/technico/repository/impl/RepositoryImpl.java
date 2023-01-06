package com.tsolas.technico.repository.impl;

import com.tsolas.technico.model.PersistentClass;
import com.tsolas.technico.repository.Repository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

public class RepositoryImpl<T extends PersistentClass> implements Repository<T> {

  @PersistenceContext(unitName = "Persistence")
  private EntityManager entityManager;

  @Override
  @Transactional
  public int create(T t) {
    entityManager.persist(t);
    return t.getId();
  }

  @Override
  public String checkRole(String username, String password) {
    try {
      return entityManager.createQuery("select u.role from AppUser u where username=:u1 and password=:u2")
              .setParameter("u1", username)
              .setParameter("u2", password)
              .getSingleResult()
              .toString();
    } catch (Exception e) {
      return "";
    }
  }

}
