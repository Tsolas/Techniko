package com.tsolas.technico.repository;

import com.tsolas.technico.model.PropertyOwner;
import java.util.List;

public interface PropertyOwnerRepository extends Repository<PropertyOwner> {

  PropertyOwner findbyVat(int vat);

  PropertyOwner findbyEmail(String email);

  List<PropertyOwner> findEmails(String email);

  List<PropertyOwner> findVats(int vat, int id);

  List<PropertyOwner> findUsernames(String username);

  String checkRole(String username, String password);

  PropertyOwner findByUserameAndPass(String username, String password);
}
