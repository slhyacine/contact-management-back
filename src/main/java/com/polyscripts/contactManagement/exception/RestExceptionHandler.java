package com.polyscripts.contactManagement.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

 @ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(ContactNotFoundException.class)
  public ResponseEntity<Object> handleContactNotFoundException(
          ContactNotFoundException exception
  ) {
      ErrorResponse errorResponse = new ErrorResponse();
      errorResponse.setStatus(HttpStatus.NOT_FOUND);
      errorResponse.setMessage(exception.getMessage());
      errorResponse.setMessage(exception.getLocalizedMessage());
      return new ResponseEntity<Object>(errorResponse, HttpStatus.NOT_FOUND);
  }

     @Override
     protected ResponseEntity<Object> handleNoHandlerFoundException(
             NoHandlerFoundException ex,
             HttpHeaders headers,
             HttpStatus status,
             WebRequest request
     ) {
         return super.handleNoHandlerFoundException(ex, headers, status, request);
     }
 }
