package com.tsolas.technico.repository;

import com.tsolas.technico.model.Property;
import java.util.List;

public interface PropertyRepository extends Repository<Property> {

  Property findbyE9(int e9);

  List<Property> findE9s(int e9);
}
