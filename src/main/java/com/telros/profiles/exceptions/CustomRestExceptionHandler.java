package com.telros.profiles.exceptions;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomRestExceptionHandler {
  @ExceptionHandler({ProfileNotFoundException.class})
  public ResponseEntity<Object> handleUserNotFoundException(
      ProfileNotFoundException ex) {
    ErrorResponse errorResponse =
        new ErrorResponse(HttpStatus.NOT_FOUND, ex.getLocalizedMessage());
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
  }

  @ExceptionHandler({HttpMessageNotReadableException.class})
  public ResponseEntity<Object> handleHttpMessageNotReadableException(
      HttpMessageNotReadableException ex) {
    ErrorResponse errorResponse =
        new ErrorResponse(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage());
    return new ResponseEntity<>(
        errorResponse, new HttpHeaders(), errorResponse.status());
  }
  @ExceptionHandler({ConstraintViolationException.class})
  public ResponseEntity<Object> handleHttpMessageNotReadableException(
      ConstraintViolationException ex) {
    ErrorResponse errorResponse =
        new ErrorResponse(HttpStatus.BAD_REQUEST, ex.getConstraintViolations().toString());
    return new ResponseEntity<>(
        errorResponse, new HttpHeaders(), errorResponse.status());
  }
}