package com.telros.profiles.exceptions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomRestExceptionHandler {

  /**
   * Handles ProfileNotFoundException, which is thrown
   * when the searched in the DB profile is absent
   *
   * @param ex exception
   * @return response with HTTP code and exception message
   */
  @ExceptionHandler({ProfileNotFoundException.class})
  public ResponseEntity<Object> handleUserNotFoundException(
      ProfileNotFoundException ex) {
    ErrorResponse errorResponse =
        new ErrorResponse(HttpStatus.NOT_FOUND, ex.getLocalizedMessage());
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
  }

  /**
   * Handles HttpMessageNotReadableException, which is thrown
   * when the HTTP request is invalid and cannot be parsed into
   * entity of request class
   *
   * @param ex exception
   * @return response with HTTP code and exception message
   */
  @ExceptionHandler({HttpMessageNotReadableException.class})
  public ResponseEntity<Object> handleHttpMessageNotReadableException(
      HttpMessageNotReadableException ex) {
    ErrorResponse errorResponse =
        new ErrorResponse(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage());
    return new ResponseEntity<>(
        errorResponse, new HttpHeaders(), errorResponse.status());
  }

  /**
   * Handles MethodArgumentNotValidException, which is thrown
   * when required arguments are absent. If the constraints are violated at the same time -
   * they are handled here as well
   *
   * @param ex exception
   * @return response with HTTP code and exception message
   */
  @ExceptionHandler({MethodArgumentNotValidException.class})
  public ResponseEntity<Object> handleMethodArgumentNotValidException(
      MethodArgumentNotValidException ex) {
    ErrorResponse errorResponse =
        new ErrorResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
    Map<String, List<String>> body = new HashMap<>();
    List<String> errors = ex.getBindingResult()
        .getFieldErrors()
        .stream()
        .map(DefaultMessageSourceResolvable::getDefaultMessage)
        .toList();
    body.put("errors", errors);
    return new ResponseEntity<>(
        body, new HttpHeaders(), errorResponse.status());
  }

  /**
   * Handles DataIntegrityViolationException, which is thrown
   * when the request entity contains parameters of entity, which must be unique
   * but already present in the DB (email, phone number)
   *
   * @param ex exception
   * @return response with HTTP code and exception message
   */
  @ExceptionHandler({DataIntegrityViolationException.class})
  public ResponseEntity<Object> handlerDataIntegrityViolationException(
      DataIntegrityViolationException ex) {
    ErrorResponse errorResponse =
        new ErrorResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
    return new ResponseEntity<>(
        errorResponse, new HttpHeaders(), errorResponse.status());
  }
}