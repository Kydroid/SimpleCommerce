package com.example.kydroid.catalog.domain.usecases;

import com.example.kydroid.catalog.domain.entities.exceptions.ResourceNotFoundException;
import com.example.kydroid.catalog.domain.entities.product.Product;
import com.example.kydroid.catalog.domain.ports.input.ExistsProduct;
import com.example.kydroid.catalog.domain.ports.input.UpdateProduct;
import com.example.kydroid.catalog.domain.ports.output.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
class UpdateProductImpl implements UpdateProduct {

    private final ProductRepository productRepository;
    private final ExistsProduct existsProduct;

    @Autowired
    public UpdateProductImpl(ProductRepository productRepository, ExistsProduct existsProduct) {
        this.productRepository = productRepository;
        this.existsProduct = existsProduct;
    }

    @Override
    public Product by(Product productToUpdate) throws ResourceNotFoundException {
        if (productToUpdate == null || !existsProduct.byId(productToUpdate.getId())) {
            Integer productId = (productToUpdate != null)
                    ? productToUpdate.getId() : null;
            throw new ResourceNotFoundException(Product.class.getSimpleName(), productId);
        }

        return productRepository.saveAndFlush(productToUpdate);
    }
}
