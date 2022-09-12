package com.clevershuttle.fleetmanager.services;

import com.clevershuttle.fleetmanager.apimodel.Car;
import com.clevershuttle.fleetmanager.apimodel.Car.StatusEnum;
import com.clevershuttle.fleetmanager.exceptions.ResourceNotFoundException;
import com.clevershuttle.fleetmanager.persistence.model.CarEntity;
import com.clevershuttle.fleetmanager.persistence.repo.CarRepositoryDAO;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class FleetManagerService {

  private final CarRepositoryDAO carRepositoryDAO;

  public FleetManagerService(CarRepositoryDAO carRepositoryDAO) {
    this.carRepositoryDAO = carRepositoryDAO;
  }

  public Car findCarById(int carId) {
    Optional<CarEntity> carEntityOptional = carRepositoryDAO.findById(carId);

    if (!carEntityOptional.isPresent()) {
      throw new ResourceNotFoundException("The searched car with id: " + carId + " was not found in the system.");
    }

    CarEntity carEntity = carEntityOptional.get();

    //TODO take into account the conversion of dashes
    return new Car()
        .id(carEntity.getId())
        .brand(carEntity.getBrand())
        .licensePlate(carEntity.getLicensePlate())
        .manufacturer(carEntity.getManufacturer())
        .operationsCity(carEntity.getOperationsCity())
        .status(StatusEnum.fromValue(carEntity.getStatus().toString()))
        .createdAt(carEntity.getBrand());
  }
}
