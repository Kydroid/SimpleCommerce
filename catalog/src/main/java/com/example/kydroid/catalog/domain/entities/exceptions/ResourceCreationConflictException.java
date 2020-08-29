package com.example.kydroid.catalog.domain.entities.exceptions;

public class ResourceCreationConflictException extends Exception {

    public ResourceCreationConflictException(String resourceName) {
        super(String.format("Resource (%s) creation conflict !", resourceName));
    }
}
