package com.clevershuttle.cars.api;

import com.clevershuttle.cars.apimodel.Car;
import com.clevershuttle.cars.services.CarsService;
import java.net.URI;
import java.util.Collections;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Controller
public class CarsApiImpl implements CarsApiDelegate
{
  private final CarsService carsService;

  public CarsApiImpl(CarsService carsService) {
    this.carsService = carsService;
  }

  @Override
  public ResponseEntity<Void> createCar(Car car){
        int idForNewCar = carsService.createCar(car);

    final URI location =
        ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(idForNewCar)
            .toUri();
        return ResponseEntity.noContent().location(location).build();
  }


  @Override
  public ResponseEntity<List<Car>> getCarById(Integer id){
    Car carData = carsService.findCarById(id);
    return ResponseEntity.ok().body(Collections.singletonList(carData));
  }

}