package com.zeustesta.apirest.ExceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler
  public ResponseEntity<String> handlerArgumentException(IllegalArgumentException exception) {
    return new ResponseEntity<String>(exception.getMessage(), HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler
  public ResponseEntity<String> handlerRuntimeException(RuntimeException exception) {
    return new ResponseEntity<String>(exception.getMessage(), HttpStatus.BAD_GATEWAY);
  }
}
