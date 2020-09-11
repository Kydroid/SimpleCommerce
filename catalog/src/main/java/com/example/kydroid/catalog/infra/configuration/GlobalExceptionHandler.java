package com.example.kydroid.catalog.infra.configuration;

import com.example.kydroid.catalog.domain.entities.exceptions.ResourceCreationConflictException;
import com.example.kydroid.catalog.domain.entities.exceptions.ResourceCreationFailedException;
import com.example.kydroid.catalog.domain.entities.exceptions.ResourceNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    @ExceptionHandler({ResourceNotFoundException.class})
    public ResourceNotFoundException handleResourceNotFoundException(ResourceNotFoundException resourceNotFoundException) {
        return resourceNotFoundException;
    }

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler({ResourceCreationFailedException.class})
    public ResourceCreationFailedException handleResourceCreationFailedException(ResourceCreationFailedException resourceCreationFailedException) {
        return resourceCreationFailedException;
    }

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler({DataIntegrityViolationException.class})
    public ResourceCreationFailedException handleDataIntegrityViolationException(DataIntegrityViolationException dataIntegrityViolationException) {
        return new ResourceCreationFailedException(dataIntegrityViolationException.getCause().getMessage());
    }

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler({ConstraintViolationException.class})
    public ConstraintViolationException handleConstraintViolationException(ConstraintViolationException constraintViolationException) {
        return constraintViolationException;
    }

    @ResponseStatus(code = HttpStatus.CONFLICT)
    @ExceptionHandler({ResourceCreationConflictException.class})
    public ResourceCreationConflictException handleResourceCreationConflictException(ResourceCreationConflictException resourceCreationConflictException) {
        return resourceCreationConflictException;
    }

}
