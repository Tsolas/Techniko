package com.tsolas.technico.model;

import com.tsolas.technico.enums.RepairStatus;
import com.tsolas.technico.enums.RepairType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity(name = "repair")
@Table
public class Repair extends PersistentClass {

  @ManyToOne
  @JoinColumn(name = "property_id")
  private Property property;
  @Enumerated(EnumType.STRING)
  private RepairType repairType;
  private String repairDescription;
  private String submissionDate;
  private String workDescription;
  private String startDate;
  private String endDate;
  private double cost;
  private boolean acceptance;
  @Enumerated(EnumType.STRING)
  private RepairStatus repairStatus;
  private String actualStartDate;
  private String actualEndDate;

}
