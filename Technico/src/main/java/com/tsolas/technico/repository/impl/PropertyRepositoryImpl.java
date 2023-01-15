package com.tsolas.technico.repository.impl;

import com.tsolas.technico.model.Property;
import com.tsolas.technico.repository.PropertyRepository;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import java.util.List;

public class PropertyRepositoryImpl extends RepositoryImpl<Property> implements PropertyRepository {

  @Override
  public Class<Property> getClassType() {
    return Property.class;
  }

  @Override
  public String getClassName() {
    return "property";
  }

  @Override
  public Property findbyE9(int e9) {
    return em.createQuery("SELECT p from property p where p.e9 =:e9", Property.class)
            .setParameter("e9", e9).getSingleResult();
  }

  @Override
  @Transactional
  public boolean delete(int id) {
    Property property = read(id);
    Query query = em.createQuery("UPDATE repair r SET r.property = null WHERE r.property = :property");
    query.setParameter("property", property);
    query.executeUpdate();
    em.remove(property);
    if (property == null) {
      return false;
    }
    em.remove(property);
    return true;
  }

  @Override
  public List<Property> findE9s(int e9) {
    return em.createQuery("SELECT p FROM property p WHERE p.e9 = :e9", Property.class)
            .setParameter("e9", e9)
            .getResultList();
  }

}
