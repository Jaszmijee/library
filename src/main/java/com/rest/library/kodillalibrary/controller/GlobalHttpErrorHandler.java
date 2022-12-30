package com.rest.library.kodillalibrary.controller;

import com.rest.library.kodillalibrary.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalHttpErrorHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(UserExistsException.class)
    public ResponseEntity<Object> handleUserAlreadyException(UserExistsException exception) {
        return new ResponseEntity<>("User already exists", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CopyNotFoundException.class)
    public ResponseEntity<Object> handleCopyNotFoundException(CopyNotFoundException exception) {
        return new ResponseEntity<>("There is no such book available", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Object> handleUserNotFound(UserNotFoundException exception) {
        return new ResponseEntity<>("User not found", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CopyNotAvailableException.class)
    public ResponseEntity<Object> handlecopyNotAvailableException(CopyNotFoundException exception) {
        return new ResponseEntity<>("Books are not available at the moment, try again later", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RentalNotFoundException.class)
    public ResponseEntity<Object> handleRentalNotFoundException(RentalNotFoundException exception) {
        return new ResponseEntity<>("No rental found with given Id", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RentalCompletedException.class)
    public ResponseEntity<Object> handleRentalCompletedException(RentalCompletedException exception) {
        return new ResponseEntity<>("All books are already returned", HttpStatus.BAD_REQUEST);
    }
}