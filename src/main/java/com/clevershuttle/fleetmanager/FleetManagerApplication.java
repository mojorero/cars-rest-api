package com.clevershuttle.fleetmanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.clevershuttle.fleetmanager.persistence.repo")
@EntityScan("com.clevershuttle.fleetmanager.persistence.model")
public class FleetManagerApplication {
  public static void main(String[] args) {
    SpringApplication.run(FleetManagerApplication.class, args);
  }
}
