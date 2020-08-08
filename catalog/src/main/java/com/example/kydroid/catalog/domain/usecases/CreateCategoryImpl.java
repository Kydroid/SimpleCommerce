package com.example.kydroid.catalog.domain.usecases;

import com.example.kydroid.catalog.domain.entities.category.Category;
import com.example.kydroid.catalog.domain.entities.exceptions.ResourceCreationConflictException;
import com.example.kydroid.catalog.domain.entities.exceptions.ResourceCreationFailedException;
import com.example.kydroid.catalog.domain.ports.input.CreateCategory;
import com.example.kydroid.catalog.domain.ports.output.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
class CreateCategoryImpl implements CreateCategory {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CreateCategoryImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category add(Category categoryTodAdd) throws ResourceCreationConflictException, ResourceCreationFailedException {
        if (categoryTodAdd.isAlreadyPersisted()) {
            throw new ResourceCreationConflictException(Category.class.getSimpleName());
        }

        Category categoryAdded = categoryRepository.saveAndFlush(categoryTodAdd);
        if (categoryAdded == null) {
            throw new ResourceCreationFailedException(Category.class.getSimpleName());
        }
        return categoryAdded;
    }
}
