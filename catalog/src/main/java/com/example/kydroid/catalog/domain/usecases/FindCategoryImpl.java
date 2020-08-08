package com.example.kydroid.catalog.domain.usecases;

import com.example.kydroid.catalog.domain.entities.category.Category;
import com.example.kydroid.catalog.domain.entities.exceptions.ResourceNotFoundException;
import com.example.kydroid.catalog.domain.ports.input.FindCategory;
import com.example.kydroid.catalog.domain.ports.output.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
class FindCategoryImpl implements FindCategory {

    private final CategoryRepository categoryRepository;

    @Autowired
    public FindCategoryImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> all() {
        return categoryRepository.findAll();
    }

    @Override
    public Category byId(Integer categoryId) throws ResourceNotFoundException {
        Optional<Category> categoryOptional = categoryRepository.findById(categoryId);
        Category categoryFounded = categoryOptional.orElseThrow(
                () -> new ResourceNotFoundException(Category.class.getSimpleName(), categoryId));
        return categoryFounded;
    }
}
