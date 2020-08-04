package com.example.kydroid.catalog.domain.ports.input;

import com.example.kydroid.catalog.domain.entities.category.Category;
import com.example.kydroid.catalog.domain.entities.exceptions.ResourceCreationConflictException;
import com.example.kydroid.catalog.domain.entities.exceptions.ResourceCreationFailedException;

public interface CreateCategory {
    Category add(Category categoryTodAdd) throws ResourceCreationConflictException, ResourceCreationFailedException;
}
