package com.clevershuttle.fleetmanager.api;

import com.clevershuttle.fleetmanager.exceptions.ConflictException;
import com.clevershuttle.fleetmanager.exceptions.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * This class is a centralized exception manager which intercepts the exceptions specified in the
 * ExceptionHandler methods. The intercepted exceptions have to be launched from methods/classes
 * which are annotated with @Controller.
 */
@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
  private static final Logger logger = LoggerFactory.getLogger(RestResponseEntityExceptionHandler.class);

  @ExceptionHandler({ ResourceNotFoundException.class })
  public ResponseEntity<Object> handleNotFoundException(RuntimeException ex) {
    logger.info(ex.getMessage());
    return new ResponseEntity<>(
        ex.getMessage(), new HttpHeaders(), HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler({ ConflictException.class })
  public ResponseEntity<Object> handleConflictException(RuntimeException ex) {
    logger.info(ex.getMessage());
    return new ResponseEntity<>(
        ex.getMessage(), new HttpHeaders(), HttpStatus.CONFLICT);
  }
}
