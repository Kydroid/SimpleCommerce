package com.example.kydroid.catalog.domain.ports.input;

import com.example.kydroid.catalog.domain.entities.exceptions.ResourceNotFoundException;

public interface DeleteProduct {
    void byId(Integer productId) throws ResourceNotFoundException;
}
