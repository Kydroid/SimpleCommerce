package com.example.kydroid.catalog.domain.entities.exceptions;

public class ResourceCreationFailedException extends Exception {

    public ResourceCreationFailedException(String resourceName) {
        super(String.format("Resource (%s) creation failed !!!", resourceName));
    }
}
