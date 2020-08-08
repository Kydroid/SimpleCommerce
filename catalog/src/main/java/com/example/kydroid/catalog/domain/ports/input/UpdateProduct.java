package com.example.kydroid.catalog.domain.ports.input;

import com.example.kydroid.catalog.domain.entities.exceptions.ResourceNotFoundException;
import com.example.kydroid.catalog.domain.entities.product.Product;

public interface UpdateProduct {
    Product by(Product productToUpdate) throws ResourceNotFoundException;
}
