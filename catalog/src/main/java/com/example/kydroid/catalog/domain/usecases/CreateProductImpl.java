package com.example.kydroid.catalog.domain.usecases;

import com.example.kydroid.catalog.domain.entities.exceptions.ResourceCreationConflictException;
import com.example.kydroid.catalog.domain.entities.exceptions.ResourceCreationFailedException;
import com.example.kydroid.catalog.domain.entities.product.Product;
import com.example.kydroid.catalog.domain.ports.input.CreateProduct;
import com.example.kydroid.catalog.domain.ports.output.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
class CreateProductImpl implements CreateProduct {

    private final ProductRepository productRepository;

    @Autowired
    public CreateProductImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product add(Product productToAdd) throws ResourceCreationFailedException, ResourceCreationConflictException {
        if (productToAdd.isAlreadyPersisted()) {
            throw new ResourceCreationConflictException(Product.class.getSimpleName());
        }

        Product productAdded = productRepository.saveAndFlush(productToAdd);
        if (productAdded == null) {
            throw new ResourceCreationFailedException(Product.class.getSimpleName());
        }
        return productAdded;
    }
}
