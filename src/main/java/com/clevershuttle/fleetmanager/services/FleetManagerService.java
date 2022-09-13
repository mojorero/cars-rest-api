package com.clevershuttle.fleetmanager.services;

import com.clevershuttle.fleetmanager.apimodel.Car;
import com.clevershuttle.fleetmanager.apimodel.Car.StatusEnum;
import com.clevershuttle.fleetmanager.exceptions.ConflictException;
import com.clevershuttle.fleetmanager.exceptions.ResourceNotFoundException;
import com.clevershuttle.fleetmanager.persistence.model.CarEntity;
import com.clevershuttle.fleetmanager.persistence.model.Status;
import com.clevershuttle.fleetmanager.persistence.repo.CarRepositoryDAO;
import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class FleetManagerService {

  public static final int FRACTIONAL_DIGITS_MILLIS = 3;
  private final CarRepositoryDAO carRepositoryDAO;

  public FleetManagerService(CarRepositoryDAO carRepositoryDAO) {
    this.carRepositoryDAO = carRepositoryDAO;
  }

  public int createCar(Car carApiData){
    if(carRepositoryDAO.existsCarEntitiesById(carApiData.getId())){
      throw new ConflictException("The car with id " + carApiData.getId() +
          " could not be created because it is already present in the system.");
    }

    Instant createdAt = Instant.parse(carApiData.getCreatedAt());
    Instant lastUpdatedAt = Instant.parse(carApiData.getLastUpdatedAt());

    CarEntity carEntity = new CarEntity.CarEntityBuilder()
        .brand(carApiData.getBrand())
        .licensePlate(carApiData.getLicensePlate())
        .manufacturer(carApiData.getManufacturer())
        .operationsCity(carApiData.getOperationsCity())
        .status(Status.valueOf(carApiData.getStatus().getValue().toUpperCase()))
        .createdAt(createdAt)
        .lastUpdatedAt(lastUpdatedAt).build();

    return carRepositoryDAO.save(carEntity).getId();
  }

  public Car findCarById(int carId) {
    Optional<CarEntity> carEntityOptional = carRepositoryDAO.findById(carId);

    if (!carEntityOptional.isPresent()) {
      throw new ResourceNotFoundException("The searched car with id: " + carId + " was not found in the system.");
    }

    CarEntity carEntity = carEntityOptional.get();

    DateTimeFormatter formatter = new DateTimeFormatterBuilder().appendInstant(
        FRACTIONAL_DIGITS_MILLIS).toFormatter();
    return new Car()
        .id(carEntity.getId())
        .brand(carEntity.getBrand())
        .licensePlate(carEntity.getLicensePlate())
        .manufacturer(carEntity.getManufacturer())
        .operationsCity(carEntity.getOperationsCity())
        .status(StatusEnum.fromValue(carEntity.getStatus().toString()))
        .createdAt(formatter.format(carEntity.getCreatedAt()))
        .lastUpdatedAt(formatter.format(carEntity.getLastUpdatedAt()));
  }
}
