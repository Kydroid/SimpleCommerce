package com.example.kydroid.catalog.domain.ports.input;

import com.example.kydroid.catalog.domain.entities.category.Category;
import com.example.kydroid.catalog.domain.entities.exceptions.ResourceNotFoundException;

public interface UpdateCategory {
    Category by(Category category) throws ResourceNotFoundException;
}
