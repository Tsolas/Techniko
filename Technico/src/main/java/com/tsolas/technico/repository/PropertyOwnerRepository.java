package com.tsolas.technico.repository;

import com.tsolas.technico.model.PropertyOwner;
import java.util.List;

public interface PropertyOwnerRepository extends Repository<PropertyOwner> {

  List<PropertyOwner> findbyVat(int vat);

  List<PropertyOwner> findbyEmail(String email);

  List<PropertyOwner> findEmails(String email, int id);

  List<PropertyOwner> findVats(int vat, int id);

  List<PropertyOwner> findUsernames(String username, int id);

  String checkRole(String username, String password);

  PropertyOwner findByUserameAndPass(String username, String password);
}
