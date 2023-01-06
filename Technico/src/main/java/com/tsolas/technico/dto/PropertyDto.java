package com.tsolas.technico.dto;

import com.tsolas.technico.enums.PropertyType;
import com.tsolas.technico.model.Property;
import com.tsolas.technico.model.PropertyOwner;
import com.tsolas.technico.model.Repair;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PropertyDto {

  private int id;
  private int e9;
  private String address;
  private String yearOfConstruction;
  private PropertyType propertyType;
  private PropertyOwner owner;
  private List<Repair> repairs;

  public PropertyDto(Property property) {
    if (property != null) {
      this.id = property.getId();
      this.e9 = property.getE9();
      this.address = property.getAddress();
      this.yearOfConstruction = property.getYearOfConstruction();
      this.propertyType = property.getPropertyType();
      this.owner = property.getOwner();
      this.repairs = property.getRepairs();
    }
  }

  public Property asProperty() {
    Property property = new Property();
    property.setE9(e9);
    property.setAddress(address);
    property.setYearOfConstruction(yearOfConstruction);
    property.setPropertyType(propertyType);
    return property;
  }

}
