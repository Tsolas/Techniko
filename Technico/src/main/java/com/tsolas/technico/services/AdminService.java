package com.tsolas.technico.services;

import com.tsolas.technico.model.Repair;
import java.util.List;

public interface AdminService {

  /**
   * Returns a list of the pending repairs
   *
   * @return
   */
  List<Repair> getPendingRepairs();

  /**
   * This method updates the cost value to the proposed by the admin
   *
   * @param repair
   * @param cost
   */
  void proposeCosts(Repair repair, double cost);

  /**
   * This method sets the proposed by the admin Dates of the repair.
   *
   * @param repair
   * @param startDate
   * @param endDate
   */
  void proposeDates(Repair repair, String startDate, String endDate);

  /**
   * Returns a list of the final start and end dates of the repairs
   */
  List<Repair> getActualDatesOfPendingRepairs();
}
