package com.carscompany.cars.services;

import com.carscompany.cars.apimodel.Car;
import com.carscompany.cars.exceptions.ResourceNotFoundException;
import com.carscompany.cars.persistence.model.CarEntity;
import com.carscompany.cars.persistence.repo.CarRepositoryDAO;
import org.jspecify.annotations.NullMarked;
import org.springframework.stereotype.Service;

@Service
@NullMarked
public class CarsService {

  private final CarRepositoryDAO carRepositoryDAO;
  private final CarMapper carMapper;

  public CarsService(CarRepositoryDAO carRepositoryDAO, CarMapper carMapper) {
    this.carRepositoryDAO = carRepositoryDAO;
    this.carMapper = carMapper;
  }

  public int createCar(Car carApiData) {
    CarEntity carEntity = carMapper.toEntity(carApiData);
    return carRepositoryDAO.save(carEntity).getId();
  }

  public Car findCarById(int carId) {
    CarEntity carEntity = carRepositoryDAO.findById(carId)
        .orElseThrow(() -> new ResourceNotFoundException(
            "The searched car with id: " + carId + " was not found in the system."));

    return carMapper.toApi(carEntity);
  }
}