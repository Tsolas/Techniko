package com.tsolas.technico.services;

import com.tsolas.technico.dto.PropertyOwnerDto;
import java.util.List;

public interface PropertyOwnerService {

  /**
   * Adds a new Owner
   *
   * @param ownerDto
   * @return
   */
  PropertyOwnerDto addNewOwner(PropertyOwnerDto ownerDto);

  /**
   * deletes an owner
   *
   * @param id
   * @return
   */
  boolean deletePropertyOwner(int id);

  /**
   * Fetches an Owner based on his id.
   *
   * @param id
   * @return
   */
  PropertyOwnerDto getOwner(int id);

  /**
   * Fetches a list of all the owners
   *
   * @return
   */
  List<PropertyOwnerDto> getAllOwners();

  /**
   * Fetches an Owner based on his vat number.
   *
   * @param vat
   * @return
   */
  PropertyOwnerDto getOwnerByVat(int vat);

  /**
   * Fetches an Owner based on his e-mail address.
   *
   * @param email
   * @return
   */
  PropertyOwnerDto getOwnerByEmail(String email);

  /**
   * Changes an owner's address.
   *
   * @param id
   * @param newAddress
   * @return
   */
  PropertyOwnerDto changeAddress(int id, String newAddress);

  PropertyOwnerDto changeEmail(int id, String email);

  PropertyOwnerDto changeName(int id, String name);

  PropertyOwnerDto changePassword(int id, String password);

  PropertyOwnerDto changePhoneNumber(int id, String phoneNumber);

  PropertyOwnerDto changeSurname(int id, String surname);

  PropertyOwnerDto changeUsername(int id, String username);

  PropertyOwnerDto changeVat(int id, int vat);

  PropertyOwnerDto getUser(String authorization);
}
