package com.example.kydroid.catalog.domain.usecases;

import com.example.kydroid.catalog.domain.ports.input.ExistsProduct;
import com.example.kydroid.catalog.domain.ports.output.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
class ExistsProductImpl implements ExistsProduct {

    private final ProductRepository productRepository;

    @Autowired
    public ExistsProductImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Boolean byId(Integer productId) {
        return productRepository.existsById(productId);
    }
}
