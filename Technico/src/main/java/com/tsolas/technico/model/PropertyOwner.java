package com.tsolas.technico.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity(name = "propertyowner")
@Table
public class PropertyOwner extends PersistentClass {

  @Column(unique = true)
  private int vat;
  private String name;
  private String surname;
  private String address;
  private String phoneNumber;
  @Column(unique = true)
  private String email;
  @Column(unique = true)
  private String username;
  private String password;
  private String role;
  @OneToMany(mappedBy = "owner", orphanRemoval = true)
  private List<Property> properties;
}
