package com.example.kydroid.catalog.domain.ports.output;

import com.example.kydroid.catalog.domain.entities.product.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findByTitleContainingIgnoreCase(String title, Pageable pageable);

    Long countByTitleContainingIgnoreCase(String title);
}
