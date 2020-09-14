package com.example.kydroid.catalog.domain.ports.input;

import com.example.kydroid.catalog.domain.entities.exceptions.ResourceNotFoundException;
import com.example.kydroid.catalog.domain.entities.product.Product;

import java.util.List;

public interface FindProduct {
    List<Product> all();

    List<Product> all(Integer page, Integer pageSize);

    Long allCount();

    Product byId(Integer productId) throws ResourceNotFoundException;

    List<Product> byTitleContainingIgnoreCase(Integer page, Integer pageSize, String title);

    Long byTitleContainingIgnoreCaseCount(String title);

    List<Product> lastUpdatedLimitedTo(int limit);
}
