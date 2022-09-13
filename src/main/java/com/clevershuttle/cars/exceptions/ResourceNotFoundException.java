package com.clevershuttle.cars.exceptions;

/**
 * Exception to manage HTTP Status 404.
 */
public class ResourceNotFoundException extends RuntimeException {
  public ResourceNotFoundException(String message){
    super(message);
  }
}
