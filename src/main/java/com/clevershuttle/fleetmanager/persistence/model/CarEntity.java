package com.clevershuttle.fleetmanager.persistence.model;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class CarEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int id;

  @Column(nullable = false)
  private String brand;

  @Column(nullable = false)
  private String licensePlate;

  @Column
  private String manufacturer;

  @Column
  private String operationsCity;

  //TODO We can't declare ENUM literals with names which contain dash (-), as that is illegal naming in java.
  // later we have to see how to map the enum name to Json name
  @Enumerated(EnumType.STRING)
  private Status status;

  @Column(nullable = false)
  private LocalDateTime createdAt;

  @Column(nullable = false)
  private LocalDateTime lastUpdatedAt;
}
