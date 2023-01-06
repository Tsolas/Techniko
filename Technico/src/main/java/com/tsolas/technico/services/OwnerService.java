package com.tsolas.technico.services;

import com.tsolas.technico.dto.PropertyDto;
import com.tsolas.technico.dto.PropertyOwnerDto;
import com.tsolas.technico.dto.RepairDto;
import com.tsolas.technico.dto.RestApiResult;
import com.tsolas.technico.exceptions.PropertyException;
import com.tsolas.technico.model.Property;
import com.tsolas.technico.model.PropertyOwner;
import com.tsolas.technico.model.Repair;

public interface OwnerService {

  /**
   * This method registers a new Owner that got inserted in the console.
   */
  void registerNewOwner();

  /**
   * This method registers a new Property that the owner inserted in the
   * console.
   *
   * @param propertyOwner
   */
  void registerNewProperty(PropertyOwner propertyOwner);

  /**
   * This method gets a new Property from the console. To be used in registering
   * a new property by its owner.
   *
   * @param propertyOwner
   * @return
   */
  Property getPropertyFromConsole(PropertyOwner propertyOwner);

  /**
   * This method gets a anew Property owner from the console. To be used in
   * registering a new owner.
   *
   * @return
   */
  PropertyOwner getOwnerFromConsole();

  /**
   * This method gets a new Repair from the console. To be used in registering a
   * new Repair.
   *
   * @param property
   * @return
   */
  Repair getRepairFromConsole(Property property);

  /**
   * This method registers a new repair that the owner inserted in the console.
   *
   * @param property
   */
  void registerRepair(Property property);

  void displayAllOwners();

  void displayOwnersProperties(int ownerId);

  /**
   * This method checks if the property inserted fulfills the business
   * requirements
   *
   * @param property
   * @throws com.tsolas.technico.exceptions.PropertyException
   */
  void isValidProperty(Property property) throws PropertyException;

  /**
   * This method changes acceptance status of a repair to true
   *
   * @param repair
   */
  void acceptRepair(Repair repair);

  /**
   * This method changes acceptance status of a repair to false
   *
   * @param repair
   */
  void declineRepair(Repair repair);

  boolean deleteProperty(int id);

  void createPropertyOwner(PropertyOwnerDto ownerDto);

  void createRepair(RepairDto repair);

  RestApiResult<PropertyOwnerDto> getOwner(int ownerId);

  RestApiResult<PropertyOwnerDto> getOwnerByVat(int vat);

  RestApiResult<PropertyOwnerDto> getOwnerByEmail(String email);

  RestApiResult<PropertyDto> getProperty(int propertyId);

  RestApiResult<RepairDto> getRepair(int repairId);

  void registerNewPropertyDto(PropertyDto propertyDto);

  boolean deletePropertyOwner(int ownerId);

  boolean deleteRepair(int repairId);

  RestApiResult<PropertyOwnerDto> updateOwner(PropertyOwnerDto propertyOwnerDto, int id);

  RestApiResult<PropertyDto> updateProperty(PropertyDto propertyDto, int id);

  RestApiResult<RepairDto> updateRepair(RepairDto repairDto, int id);

}
