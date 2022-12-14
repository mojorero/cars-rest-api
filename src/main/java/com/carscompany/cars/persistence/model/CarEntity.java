package com.carscompany.cars.persistence.model;

import java.time.Instant;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class CarEntity {

  @Id
  @Setter(AccessLevel.PROTECTED)
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

  @Enumerated(EnumType.STRING)
  private Status status;

  @Column(nullable = false)
  private Instant createdAt;

  @Column(nullable = false)
  private Instant lastUpdatedAt;

  public CarEntity(){

  }

  public CarEntity(CarEntityBuilder carEntityBuilder) {
    this.brand = carEntityBuilder.brand;
    this.licensePlate = carEntityBuilder.licensePlate;
    this.manufacturer = carEntityBuilder.manufacturer;
    this.operationsCity = carEntityBuilder.operationsCity;
    this.status = carEntityBuilder.status;
    this.createdAt = carEntityBuilder.createdAt;
    this.lastUpdatedAt = carEntityBuilder.lastUpdatedAt;
  }

  public static class CarEntityBuilder {

    private String brand;

    private String licensePlate;

    private String manufacturer;

    private String operationsCity;

    private Status status;

    private Instant createdAt;

    private Instant lastUpdatedAt;

    public CarEntityBuilder brand(String brand){
      this.brand = brand;
      return this;
    }

    public CarEntityBuilder licensePlate(String licensePlate) {
      this.licensePlate = licensePlate;
      return this;
    }

    public CarEntityBuilder manufacturer(String manufacturer) {
      this.manufacturer = manufacturer;
      return this;
    }

    public CarEntityBuilder operationsCity(String operationsCity) {
      this.operationsCity = operationsCity;
      return this;
    }

    public CarEntityBuilder status(Status status) {
      this.status = status;
      return this;
    }

    public CarEntityBuilder createdAt(Instant createdAt) {
      this.createdAt = createdAt;
      return this;
    }

    public CarEntityBuilder lastUpdatedAt(Instant lastUpdatedAt) {
      this.lastUpdatedAt = lastUpdatedAt;
      return this;
    }

    public CarEntity build(){
      return new CarEntity(this);
    }
  }

}
