package com.example.kydroid.catalog.domain.ports.output;

import com.example.kydroid.catalog.domain.entities.product.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findByTitleContainingIgnoreCase(String title, Pageable pageable);

    Long countByTitleContainingIgnoreCase(String title);

    @Transactional
    @Modifying
    @Query("UPDATE Product product set product.category = null WHERE product.category.id = :categoryId")
    int updateProductsSetCategoryNullByCategoryId(@Param("categoryId") Integer categoryId);

    List<Product> findAllByOrderByLastModifiedDateDesc(Pageable pageable);
}
