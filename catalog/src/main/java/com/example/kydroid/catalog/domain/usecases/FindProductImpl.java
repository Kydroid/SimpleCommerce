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

    public final int MIN_PAGE_SIZE = 1;
    public final int MAX_PAGE_SIZE = 100;

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
    public List<Product> all(Integer page, Integer pageSize) {
        if (pageSize < MIN_PAGE_SIZE || pageSize > MAX_PAGE_SIZE) {
            throw new IllegalArgumentException(String.format("PageSize argument must be between %s and %s", MIN_PAGE_SIZE, MAX_PAGE_SIZE));
        }
        Pageable pageable = PageRequest.of(page, pageSize);
        return productRepository.findAll(pageable).getContent();
    }

    @Override
    public Long allCount() {
        return productRepository.count();
    }

    @Override
    public Product byId(Integer productId) throws ResourceNotFoundException {
        Optional<Product> productOptional = productRepository.findById(productId);
        Product productFounded = productOptional.orElseThrow(
                () -> new ResourceNotFoundException(Product.class.getSimpleName(), productId)
        );
        return productFounded;
    }

    @Override
    public List<Product> byTitleContainingIgnoreCase(Integer page, Integer pageSize, String title) {
        if (pageSize < MIN_PAGE_SIZE || pageSize > MAX_PAGE_SIZE) {
            throw new IllegalArgumentException(String.format("PageSize argument must be between %s and %s", MIN_PAGE_SIZE, MAX_PAGE_SIZE));
        }
        Pageable pageable = PageRequest.of(page, pageSize);
        return productRepository.findByTitleContainingIgnoreCase(title, pageable);
    }

    @Override
    public Long byTitleContainingIgnoreCaseCount(String title) {
        return productRepository.countByTitleContainingIgnoreCase(title);
    }
}
