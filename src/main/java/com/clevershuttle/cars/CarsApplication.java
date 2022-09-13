package com.clevershuttle.cars;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.clevershuttle.cars.persistence.repo")
@EntityScan("com.clevershuttle.cars.persistence.model")
public class CarsApplication {
  public static void main(String[] args) {
    SpringApplication.run(CarsApplication.class, args);
  }
}
