package com.example.kydroid.catalog.domain.entities.exceptions;

public class ResourceNotFoundException extends Exception {

    public ResourceNotFoundException(String resourceName, Integer resourceId) {
        super(String.format("Resource (%s with id=%s) not found !", resourceName, resourceId));
    }
}
