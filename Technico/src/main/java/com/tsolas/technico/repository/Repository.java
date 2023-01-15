package com.tsolas.technico.repository;

import com.tsolas.technico.model.PersistentClass;
import java.util.List;

public interface Repository<T extends PersistentClass> {

  T create(T t);

  T read(int id);

  List<T> readAll();

  boolean delete(int id);
}
