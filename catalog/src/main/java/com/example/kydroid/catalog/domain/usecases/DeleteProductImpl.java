package com.example.kydroid.catalog.domain.usecases;

import com.example.kydroid.catalog.domain.entities.exceptions.ResourceNotFoundException;
import com.example.kydroid.catalog.domain.entities.product.Product;
import com.example.kydroid.catalog.domain.ports.input.DeleteProduct;
import com.example.kydroid.catalog.domain.ports.input.ExistsProduct;
import com.example.kydroid.catalog.domain.ports.output.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
class DeleteProductImpl implements DeleteProduct {

    private final ProductRepository productRepository;
    private final ExistsProduct existsProduct;

    @Autowired
    public DeleteProductImpl(ProductRepository productRepository, ExistsProduct existsProduct) {
        this.productRepository = productRepository;
        this.existsProduct = existsProduct;
    }

    @Override
    public void byId(Integer productId) throws ResourceNotFoundException {
        if (!existsProduct.byId(productId)) {
            throw new ResourceNotFoundException(Product.class.getSimpleName(), productId);
        }

        productRepository.deleteById(productId);
    }
}
