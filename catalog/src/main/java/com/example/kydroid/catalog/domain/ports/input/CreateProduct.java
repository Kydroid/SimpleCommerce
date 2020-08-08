package com.example.kydroid.catalog.domain.ports.input;

import com.example.kydroid.catalog.domain.entities.exceptions.ResourceCreationConflictException;
import com.example.kydroid.catalog.domain.entities.exceptions.ResourceCreationFailedException;
import com.example.kydroid.catalog.domain.entities.product.Product;

public interface CreateProduct {
    Product add(Product productToAdd) throws ResourceCreationFailedException, ResourceCreationConflictException;
}
