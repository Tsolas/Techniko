package com.tsolas.technico.services.implementation;

import com.tsolas.technico.dto.PropertyDto;
import com.tsolas.technico.enums.PropertyType;
import com.tsolas.technico.model.Property;
import com.tsolas.technico.model.PropertyOwner;
import com.tsolas.technico.repository.PropertyOwnerRepository;
import com.tsolas.technico.repository.PropertyRepository;
import com.tsolas.technico.services.PropertyService;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PropertyServiceImpl implements PropertyService {

  private static final Logger logger = LogManager.getLogger(PropertyServiceImpl.class);

  @Inject
  private PropertyRepository propertyRepository;
  @Inject
  private PropertyOwnerRepository ownerRepository;

  @Override
  @Transactional
  public PropertyDto addNewProperty(int ownerId, PropertyDto propertyDto) {
    logger.trace("Trying to add new property");
    try {
      PropertyType.valueOf(propertyDto.getPropertyType().toString());
    } catch (IllegalArgumentException e) {
      logger.warn("Invalid PropertyType value: " + propertyDto.getPropertyType());
      throw new IllegalArgumentException("Invalid PropertyType value: " + propertyDto.getPropertyType());
    }
    Property property = propertyDto.asProperty();
    List<Property> e9List = propertyRepository.findE9s(property.getE9(), property.getId());
    if (!e9List.isEmpty()) {
      logger.warn("A property with E9: " + property.getE9() + "already exists");
      throw new IllegalArgumentException("A property with E9: " + property.getE9() + "already exists");
    }
    PropertyOwner propertyOwner = ownerRepository.read(ownerId);
    property.setOwner(propertyOwner);
    propertyOwner.getProperties().add(property);
    logger.info("Adding new property: " + propertyDto.toString());
    propertyRepository.create(property);
    ownerRepository.create(propertyOwner);
    return new PropertyDto(property);
  }

  @Override
  public boolean deleteProperty(int id) {
    boolean deleted = propertyRepository.delete(id);
    if (deleted) {
      logger.info("Deleting owner with id: " + id);
    } else {
      logger.warn("Deletion of owned with id: " + id + "failed");
    }
    return deleted;
  }

  @Override
  public PropertyDto getProperty(int id) {
    logger.trace("Trying to find property with id: " + id);
    try {
      PropertyDto property = new PropertyDto(propertyRepository.read(id));
      logger.info("Returning property: " + property.toString());
      return property;
    } catch (NullPointerException e) {
      logger.error("Error getting owner with id " + id, e);
      return null;
    }
  }

  @Override
  public List<PropertyDto> getAllProperties() {
    logger.info("Returning all properties");
    return propertyRepository.readAll().stream().map(PropertyDto::new).collect(Collectors.toList());
  }

  @Override
  public PropertyDto getPropertyByE9(int e9) {
    logger.info("Returning property with e9: " + e9);
    return new PropertyDto(propertyRepository.findbyE9(e9));
  }

  @Transactional
  @Override
  public List<PropertyDto> getPropertiesByOwnerVat(int vat) {
    PropertyOwner owner = ownerRepository.findbyVat(vat).get(0);
    logger.info("Returning properties that belong to owner with Vat : " + vat);
    return owner.getProperties().stream().map(property -> new PropertyDto(property)).collect(Collectors.toList());
  }

  @Override
  public PropertyDto changeAddress(int id, String newAddress) {
    Property property = propertyRepository.read(id);
    if (property == null) {
      logger.error("Property with id :" + id + "doesn't exist.");
      return null;
    }
    property.setAddress(newAddress);
    logger.info("Changing address of property with id: " + id + "to :" + newAddress);
    propertyRepository.create(property);
    return new PropertyDto(property);
  }

  @Override
  public PropertyDto changeYear(int id, String newYear) {
    Property property = propertyRepository.read(id);
    if (property == null) {
      logger.error("Property with id :" + id + "doesn't exist.");
      return null;
    }
    property.setYearOfConstruction(newYear);
    logger.info("Changing year of construction of property with id: " + id + "to :" + newYear);
    propertyRepository.create(property);
    return new PropertyDto(property);
  }

  @Override
  public PropertyDto changePropertyType(int id, PropertyType newPropertyType) {
    Property property = propertyRepository.read(id);
    if (property == null) {
      logger.error("Property with id :" + id + "doesn't exist.");
      return null;
    }
    try {
      PropertyType.valueOf(newPropertyType.toString());
    } catch (IllegalArgumentException e) {
      logger.error("Invalid PropertyType value: " + newPropertyType);
      throw new IllegalArgumentException("Invalid PropertyType value: " + newPropertyType);
    }
    property.setPropertyType(newPropertyType);
    logger.info("Changing property type of property with id: " + id + "to :" + newPropertyType);
    propertyRepository.create(property);
    return new PropertyDto(property);
  }

  @Override
  public PropertyDto changeE9(int id, int newE9) {
    Property property = propertyRepository.read(id);
    if (property == null) {
      logger.error("Property with id :" + id + "doesn't exist.");
      return null;
    }
    List<Property> e9List = propertyRepository.findE9s(property.getE9(), property.getId());
    if (!e9List.isEmpty()) {
      throw new IllegalArgumentException("A property with this E9 already exists");
    }
    property.setE9(newE9);
    logger.info("Changing E9 of property with id: " + id + "to :" + newE9);
    propertyRepository.create(property);
    return new PropertyDto(property);
  }

}
