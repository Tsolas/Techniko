package com.tsolas.technico.repository;

import com.tsolas.technico.model.PersistentClass;

public interface Repository<T extends PersistentClass> {

  int create(T t);

}
