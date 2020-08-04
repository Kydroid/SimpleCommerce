package com.example.kydroid.catalog.domain.ports.output;

import com.example.kydroid.catalog.domain.entities.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
