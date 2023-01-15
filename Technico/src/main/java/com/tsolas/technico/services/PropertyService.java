package com.tsolas.technico.services;

import com.tsolas.technico.dto.PropertyDto;
import com.tsolas.technico.enums.PropertyType;
import java.util.List;

public interface PropertyService {

  /**
   * Adds a new Property setting the owner it belongs to at the same time.
   *
   * @param ownerId
   * @param propertyDto
   * @return
   */
  PropertyDto addNewProperty(int ownerId, PropertyDto propertyDto);

  boolean deleteProperty(int id);

  PropertyDto getProperty(int id);

  List<PropertyDto> getAllProperties();

  PropertyDto getPropertyByE9(int e9);

  List<PropertyDto> getPropertiesByOwnerVat(int vat);

  PropertyDto changeAddress(int id, String newAddress);

  PropertyDto changeYear(int id, String newYear);

  PropertyDto changePropertyType(int id, PropertyType newPropertyType);

  PropertyDto changeE9(int id, int newE9);

}
