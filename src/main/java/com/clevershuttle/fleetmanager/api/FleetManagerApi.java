package com.clevershuttle.fleetmanager.api;

import com.clevershuttle.fleetmanager.apimodel.Car;
import com.clevershuttle.fleetmanager.services.FleetManagerService;
import java.net.URI;
import java.util.Collections;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Controller
public class FleetManagerApi implements CarsApiDelegate
{
  private final FleetManagerService fleetManagerService;

  public FleetManagerApi(FleetManagerService fleetManagerService) {
    this.fleetManagerService = fleetManagerService;
  }

  @Override
  public ResponseEntity<Void> createCar(Car car){
        int idForNewCar = fleetManagerService.createCar(car);

    final URI location =
        ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(idForNewCar)
            .toUri();
        return ResponseEntity.noContent().location(location).build();
  }


  @Override
  public ResponseEntity<List<Car>> getCarById(Integer id){
    Car carData = fleetManagerService.findCarById(id);
    return ResponseEntity.ok().body(Collections.singletonList(carData));
  }

}