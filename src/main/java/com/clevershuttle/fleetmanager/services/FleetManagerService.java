package com.clevershuttle.fleetmanager.services;

import com.clevershuttle.fleetmanager.apimodel.Car;
import com.clevershuttle.fleetmanager.apimodel.Car.StatusEnum;
import com.clevershuttle.fleetmanager.exceptions.ConflictException;
import com.clevershuttle.fleetmanager.exceptions.ResourceNotFoundException;
import com.clevershuttle.fleetmanager.persistence.model.CarEntity;
import com.clevershuttle.fleetmanager.persistence.model.Status;
import com.clevershuttle.fleetmanager.persistence.repo.CarRepositoryDAO;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class FleetManagerService {

  private final CarRepositoryDAO carRepositoryDAO;

  public FleetManagerService(CarRepositoryDAO carRepositoryDAO) {
    this.carRepositoryDAO = carRepositoryDAO;
  }

  public int createCar(Car carApiData){
    if(carRepositoryDAO.existsCarEntitiesById(carApiData.getId())){
      throw new ConflictException("The car with id " + carApiData.getId() +
          " could not be created because it is already present in the system.");
    }

    LocalDateTime createdAt = LocalDateTime.parse(carApiData.getCreatedAt(), DateTimeFormatter.ISO_DATE_TIME);
    LocalDateTime lastUpdatedAt = LocalDateTime.parse(carApiData.getLastUpdatedAt(), DateTimeFormatter.ISO_DATE_TIME);

    CarEntity carEntity = new CarEntity.CarEntityBuilder()
        .brand(carApiData.getBrand())
        .licensePlate(carApiData.getLicensePlate())
        .manufacturer(carApiData.getManufacturer())
        .operationsCity(carApiData.getOperationsCity())
        .status(Status.valueOf(carApiData.getStatus().getValue()))
//        “createdAt”: “2017-09-01T10:23:47.000Z",
        .createdAt(createdAt)
        //TODO Exception for datetimeconversion problems.
        .lastUpdatedAt(lastUpdatedAt).build();

    carRepositoryDAO.save(carEntity);
    return carEntity.getId();
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
        .createdAt(carEntity.getCreatedAt().toString())
        .lastUpdatedAt(carEntity.getLastUpdatedAt().toString());
  }
}
