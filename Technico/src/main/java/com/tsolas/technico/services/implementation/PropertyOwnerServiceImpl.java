package com.tsolas.technico.services.implementation;

import com.tsolas.technico.dto.PropertyOwnerDto;
import com.tsolas.technico.dto.RestApiResult;
import com.tsolas.technico.model.PropertyOwner;
import com.tsolas.technico.repository.PropertyOwnerRepository;
import jakarta.inject.Inject;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.tsolas.technico.services.PropertyOwnerService;
import java.util.Base64;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class PropertyOwnerServiceImpl implements PropertyOwnerService {

  private static final Logger logger = LogManager.getLogger(PropertyOwnerServiceImpl.class);

  @Inject
  protected PropertyOwnerRepository ownerRepository;

  @Override
  public RestApiResult<PropertyOwnerDto> addNewOwner(PropertyOwnerDto ownerDto) {
    PropertyOwner owner = ownerDto.asPropertyOwner();
    List<PropertyOwner> emails = ownerRepository.findEmails(owner.getEmail(), owner.getId());
    if (!emails.isEmpty()) {
      logger.warn("Email already in use by another property owner");
      return new RestApiResult<>(null, 404, "Email already in use by another property owner");
    }
    List<PropertyOwner> vats = ownerRepository.findVats(owner.getVat(), owner.getId());
    if (!vats.isEmpty()) {
      logger.warn("User with this Vat already exists");
      return new RestApiResult<>(null, 404, "User with this Vat already exists");
    }
    List<PropertyOwner> usernames = ownerRepository.findUsernames(owner.getUsername(), owner.getId());
    if (!usernames.isEmpty()) {
      logger.warn("Username already in use by another property owner");
      return new RestApiResult<>(null, 404, "Username already in use by another property owner");
    }
    ownerRepository.create(owner);
    logger.info("Adding new owner: " + ownerDto.toString());
    return new RestApiResult<>(ownerDto, 0, "User added succefully");
  }

  @Override
  public boolean deletePropertyOwner(int id) {
    logger.info("Deleting owner with id: " + id);
    return ownerRepository.delete(id);
  }

  @Override
  public PropertyOwnerDto getOwner(int id) {
    logger.info("Returning owner with id: " + id);
    return new PropertyOwnerDto(ownerRepository.read(id));
  }

  @Override
  public List<PropertyOwnerDto> getAllOwners() {
    logger.info("Getting all owners");
    return ownerRepository.readAll().stream().map(PropertyOwnerDto::new).collect(Collectors.toList());
  }

  @Override
  public RestApiResult<PropertyOwnerDto> getOwnerByVat(int vat) {
    if (ownerRepository.findbyVat(vat).isEmpty()) {
      return new RestApiResult<>(null, 404, "There is no user with this Vat");
    }
    logger.info("Returning owner with vat: " + vat);
    PropertyOwnerDto ownerDto = new PropertyOwnerDto(ownerRepository.findbyVat(vat).get(0));
    return new RestApiResult<>(ownerDto, 0, "Returning owner with vat:" + vat);
  }

  @Override
  public RestApiResult<PropertyOwnerDto> getOwnerByEmail(String email) {
    if (ownerRepository.findbyEmail(email).isEmpty()) {
      return new RestApiResult<>(null, 404, "There is no user with this e-mail");
    }
    logger.info("Getting owner with e-mail: " + email);
    PropertyOwnerDto ownerDto = new PropertyOwnerDto(ownerRepository.findbyEmail(email).get(0));
    return new RestApiResult<>(ownerDto, 0, "Returning owner with e-mail:" + email);
  }

  @Override
  public PropertyOwnerDto changeAddress(int id, String newAddress) {
    PropertyOwner propertyOwner = ownerRepository.read(id);
    if (propertyOwner == null) {
      return null;
    }
    propertyOwner.setAddress(newAddress);
    ownerRepository.create(propertyOwner);
    logger.info("Changing address of owner with id: " + id + "to :" + newAddress);
    return new PropertyOwnerDto(propertyOwner);
  }

  @Override
  public PropertyOwnerDto changeEmail(int id, String email) {
    PropertyOwner propertyOwner = ownerRepository.read(id);
    if (propertyOwner == null) {
      return null;
    }
    List<PropertyOwner> results = ownerRepository.findEmails(email, id);
    if (!results.isEmpty()) {
      throw new IllegalArgumentException("Email already in use by another property owner");
    }
    propertyOwner.setEmail(email);
    logger.info("Changing e-mail of owner with id: " + id + "to :" + email);
    ownerRepository.create(propertyOwner);
    return new PropertyOwnerDto(propertyOwner);
  }

  @Override
  public PropertyOwnerDto changeName(int id, String name) {
    PropertyOwner propertyOwner = ownerRepository.read(id);
    if (propertyOwner == null) {
      return null;
    }
    propertyOwner.setName(name);
    logger.info("Changing name of owner with id: " + id + "to :" + name);
    ownerRepository.create(propertyOwner);
    return new PropertyOwnerDto(propertyOwner);
  }

  @Override
  public PropertyOwnerDto changePassword(int id, String password) {
    PropertyOwner propertyOwner = ownerRepository.read(id);
    if (propertyOwner == null) {
      return null;
    }
    propertyOwner.setPassword(password);
    logger.info("Changing password of owner with id: " + id + "to :" + password);
    ownerRepository.create(propertyOwner);
    return new PropertyOwnerDto(propertyOwner);
  }

  @Override
  public PropertyOwnerDto changePhoneNumber(int id, String phoneNumber) {
    PropertyOwner propertyOwner = ownerRepository.read(id);
    if (propertyOwner == null) {
      return null;
    }
    propertyOwner.setPhoneNumber(phoneNumber);
    logger.info("Changing phone number of owner with id: " + id + "to :" + phoneNumber);
    ownerRepository.create(propertyOwner);
    return new PropertyOwnerDto(propertyOwner);
  }

  @Override
  public PropertyOwnerDto changeSurname(int id, String surname) {
    PropertyOwner propertyOwner = ownerRepository.read(id);
    if (propertyOwner == null) {
      return null;
    }
    propertyOwner.setSurname(surname);
    logger.info("Changing surname of owner with id: " + id + "to :" + surname);
    ownerRepository.create(propertyOwner);
    return new PropertyOwnerDto(propertyOwner);
  }

  @Override
  public PropertyOwnerDto changeUsername(int id, String username) {
    PropertyOwner propertyOwner = ownerRepository.read(id);
    if (propertyOwner == null) {
      return null;
    }
    List<PropertyOwner> usernames = ownerRepository.findUsernames(propertyOwner.getUsername(), propertyOwner.getId());
    if (!usernames.isEmpty()) {
      throw new IllegalArgumentException("Username already in use by another property owner");
    }
    propertyOwner.setUsername(username);
    logger.info("Changing username of owner with id: " + id + "to :" + username);
    ownerRepository.create(propertyOwner);
    return new PropertyOwnerDto(propertyOwner);
  }

  @Override
  public PropertyOwnerDto changeVat(int id, int vat) {
    PropertyOwner propertyOwner = ownerRepository.read(id);
    if (propertyOwner == null) {
      return null;
    }
    List<PropertyOwner> allvats = ownerRepository.findVats(propertyOwner.getVat(), id);
    if (!allvats.isEmpty()) {
      throw new IllegalArgumentException("User with this Vat already exists  " + allvats.size());
    }
    propertyOwner.setVat(vat);
    logger.info("Changing vat of owner with id: " + id + "to :" + vat);
    ownerRepository.create(propertyOwner);
    return new PropertyOwnerDto(propertyOwner);
  }

  @Override
  public PropertyOwnerDto getUser(String authorization) {
    final String encodedUserPassword = authorization.replaceFirst("Basic" + " ", "");
    String usernameAndPassword = new String(Base64.getDecoder().decode(encodedUserPassword.getBytes()));
    final StringTokenizer tokenizer = new StringTokenizer(usernameAndPassword, ":");
    final String username = tokenizer.nextToken();
    final String password = tokenizer.nextToken();
    return new PropertyOwnerDto(ownerRepository.findByUserameAndPass(username, password));
  }
}
