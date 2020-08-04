package com.example.kydroid.catalog.domain.ports.input;

import com.example.kydroid.catalog.domain.entities.exceptions.ResourceNotFoundException;

public interface DeleteCategory {

    void byId(Integer categoryId) throws ResourceNotFoundException;
}
