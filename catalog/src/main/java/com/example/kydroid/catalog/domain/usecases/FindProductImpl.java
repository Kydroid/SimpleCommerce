package com.example.kydroid.catalog.domain.usecases;

import com.example.kydroid.catalog.domain.entities.exceptions.ResourceNotFoundException;
import com.example.kydroid.catalog.domain.entities.product.Product;
import com.example.kydroid.catalog.domain.ports.input.FindProduct;
import com.example.kydroid.catalog.domain.ports.output.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
class FindProductImpl implements FindProduct {

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
    public List<Product> all(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        return productRepository.findAll(pageable).getContent();
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
