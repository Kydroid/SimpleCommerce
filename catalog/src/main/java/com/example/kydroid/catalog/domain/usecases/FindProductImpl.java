package com.example.kydroid.catalog.domain.usecases;

import com.example.kydroid.catalog.domain.entities.exceptions.ResourceNotFoundException;
import com.example.kydroid.catalog.domain.entities.product.Product;
import com.example.kydroid.catalog.domain.ports.input.FindProduct;
import com.example.kydroid.catalog.domain.ports.output.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FindProductImpl implements FindProduct {

    private final ProductRepository productRepository;

    @Autowired
    public FindProductImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> all() {
        return productRepository.findAll();
    }

    @Override
    public Product byId(Integer productId) throws ResourceNotFoundException {
        Optional<Product> productOptional = productRepository.findById(productId);
        Product productFounded = productOptional.orElseThrow(
                () -> new ResourceNotFoundException(Product.class.getSimpleName(), productId)
        );
        return productFounded;
    }
}
