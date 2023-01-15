package com.tsolas.technico.repository;

import com.tsolas.technico.model.Repair;
import java.util.List;

public interface RepairRepository extends Repository<Repair> {

  List<Repair> findPending();

  List<Repair> findbyExactDate(String date);

  List<Repair> findRepairsOfOwner(int id);
}
