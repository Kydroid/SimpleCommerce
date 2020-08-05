package com.example.kydroid.catalog.infra.configuration;

import com.example.kydroid.catalog.domain.entities.exceptions.ResourceCreationConflictException;
import com.example.kydroid.catalog.domain.entities.exceptions.ResourceCreationFailedException;
import com.example.kydroid.catalog.domain.entities.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Resource not found !!")
    @ExceptionHandler({ResourceNotFoundException.class})
    public void handleResourceNotFoundException() {
    }

    @ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Resource creation failed !")
    @ExceptionHandler({ResourceCreationFailedException.class})
    public void handleResourceCreationFailedException() {
    }

    @ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Resource validation failed !")
    @ExceptionHandler({ConstraintViolationException.class})
    public void handleConstraintViolationException() {
    }

    @ResponseStatus(code = HttpStatus.CONFLICT, reason = "Resource creation conflict !")
    @ExceptionHandler({ResourceCreationConflictException.class})
    public void handleResourceCreationConflictException() {
    }

}
