package com.tsolas.technico.repository.impl;

import com.tsolas.technico.model.PersistentClass;
import com.tsolas.technico.repository.Repository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import java.util.List;

public abstract class RepositoryImpl<T extends PersistentClass> implements Repository<T> {

  @PersistenceContext(unitName = "Persistence")
  protected EntityManager em;

  public abstract Class<T> getClassType();

  public abstract String getClassName();

  @Override
  @Transactional
  public T create(T t) {
    em.persist(em.contains(t) ? t : em.merge(t));
    return t;
  }

  @Override
  @Transactional
  public T read(int id) {
    return em.find(getClassType(), id);
  }

  @Override
  public List<T> readAll() {
    return em.createQuery("select c from " + getClassName() + " c").getResultList();
  }

  @Override
  @Transactional
  public boolean delete(int id) {
    T t = read(id);
    if (t == null) {
      return false;
    }
    em.remove(t);
    return true;
  }
}
