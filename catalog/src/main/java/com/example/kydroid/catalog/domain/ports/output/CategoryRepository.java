package com.example.kydroid.catalog.domain.ports.output;

import com.example.kydroid.catalog.domain.entities.category.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
