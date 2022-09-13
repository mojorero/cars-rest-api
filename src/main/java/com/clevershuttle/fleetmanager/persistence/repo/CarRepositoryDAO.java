package com.clevershuttle.fleetmanager.persistence.repo;

import com.clevershuttle.fleetmanager.persistence.model.CarEntity;
import org.springframework.data.repository.CrudRepository;

public interface CarRepositoryDAO extends CrudRepository<CarEntity, Integer> {
  boolean existsCarEntitiesById(Integer carId);

}
