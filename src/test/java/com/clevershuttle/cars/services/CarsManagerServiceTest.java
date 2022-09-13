package com.clevershuttle.cars.services;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;

import com.clevershuttle.cars.apimodel.Car;
import com.clevershuttle.cars.exceptions.ConflictException;
import com.clevershuttle.cars.exceptions.ResourceNotFoundException;
import com.clevershuttle.cars.persistence.repo.CarRepositoryDAO;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CarsManagerServiceTest {

  @Mock
  private CarRepositoryDAO carRepositoryDAO;

  @Mock
  private Car car;

  @InjectMocks
  private CarsService carsService;

  @Test
  void givenExistingCar_whenCreateCar_thenThrowConflictException(){
    Mockito.when(carRepositoryDAO.existsCarEntitiesById(anyInt())).thenReturn(true);
    assertThrows(ConflictException.class, () -> carsService.createCar(car));
  }

  @Test
  void givenNotExistingCar_whenFindCarById_thenThrowResourceNotFoundException(){
    Mockito.when(carRepositoryDAO.findById(anyInt())).thenReturn(Optional.empty());
    assertThrows(ResourceNotFoundException.class, () -> carsService.findCarById(1));
  }

}
