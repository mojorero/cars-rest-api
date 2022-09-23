package com.carscompany.cars.exceptions;

/**
 * Exception to manage HTTP Status 409.
 */
public class ConflictException extends RuntimeException {
  public ConflictException(String message){
    super(message);
  }
}
