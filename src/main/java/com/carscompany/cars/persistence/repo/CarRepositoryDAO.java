package com.carscompany.cars.persistence.repo;

import com.carscompany.cars.persistence.model.CarEntity;
import org.springframework.data.repository.CrudRepository;

public interface CarRepositoryDAO extends CrudRepository<CarEntity, Integer> {
  boolean existsCarEntitiesById(Integer carId);

}
