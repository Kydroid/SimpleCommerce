package com.example.kydroid.catalog.domain.ports.input;

import com.example.kydroid.catalog.domain.entities.exceptions.ResourceNotFoundException;
import com.example.kydroid.catalog.domain.entities.product.Product;

import java.util.List;

public interface FindProduct {
    List<Product> all();

    Product byId(Integer productId) throws ResourceNotFoundException;
}
