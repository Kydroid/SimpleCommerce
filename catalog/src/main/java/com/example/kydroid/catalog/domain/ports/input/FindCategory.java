package com.example.kydroid.catalog.domain.ports.input;

import com.example.kydroid.catalog.domain.entities.category.Category;
import com.example.kydroid.catalog.domain.entities.exceptions.ResourceNotFoundException;

import java.util.List;

public interface FindCategory {

    List<Category> all();

    Category byId(Integer categoryId) throws ResourceNotFoundException;
}
