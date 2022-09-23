package com.carscompany.cars.persistence.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum Status {
  @JsonProperty("available") AVAILABLE,
  @JsonProperty("in-maintenance") IN_MAINTENANCE,
  @JsonProperty("out-of-service") OUT_OF_SERVICE;

  @Override
  public String toString() {
    return super.toString().toLowerCase();
  }
}
