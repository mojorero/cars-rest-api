package com.clevershuttle.fleetmanager.services;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;

import com.clevershuttle.fleetmanager.apimodel.Car;
import com.clevershuttle.fleetmanager.exceptions.ConflictException;
import com.clevershuttle.fleetmanager.exceptions.ResourceNotFoundException;
import com.clevershuttle.fleetmanager.persistence.repo.CarRepositoryDAO;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class FleetManagerServiceTest {

  @Mock
  private CarRepositoryDAO carRepositoryDAO;

  @Mock
  private Car car;

  @InjectMocks
  private FleetManagerService fleetManagerService;

  @Test
  void givenExistingCar_whenCreateCar_thenThrowConflictException(){
    Mockito.when(carRepositoryDAO.existsCarEntitiesById(anyInt())).thenReturn(true);
    assertThrows(ConflictException.class, () -> fleetManagerService.createCar(car));
  }

  @Test
  void givenNotExistingCar_whenFindCarById_thenThrowResourceNotFoundException(){
    Mockito.when(carRepositoryDAO.findById(anyInt())).thenReturn(Optional.empty());
    assertThrows(ResourceNotFoundException.class, () -> fleetManagerService.findCarById(1));
  }

}
