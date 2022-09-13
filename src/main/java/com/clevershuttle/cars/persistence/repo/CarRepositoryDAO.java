package com.clevershuttle.cars.persistence.repo;

import com.clevershuttle.cars.persistence.model.CarEntity;
import org.springframework.data.repository.CrudRepository;

public interface CarRepositoryDAO extends CrudRepository<CarEntity, Integer> {
  boolean existsCarEntitiesById(Integer carId);

}
