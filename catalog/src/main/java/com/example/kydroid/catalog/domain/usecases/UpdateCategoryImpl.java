package com.example.kydroid.catalog.domain.usecases;

import com.example.kydroid.catalog.domain.entities.category.Category;
import com.example.kydroid.catalog.domain.entities.exceptions.ResourceNotFoundException;
import com.example.kydroid.catalog.domain.ports.input.ExistsCategory;
import com.example.kydroid.catalog.domain.ports.input.UpdateCategory;
import com.example.kydroid.catalog.domain.ports.output.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
class UpdateCategoryImpl implements UpdateCategory {

    private final CategoryRepository categoryRepository;
    private final ExistsCategory existsCategory;

    @Autowired
    public UpdateCategoryImpl(CategoryRepository categoryRepository, ExistsCategory existsCategory) {
        this.categoryRepository = categoryRepository;
        this.existsCategory = existsCategory;
    }

    @Override
    public Category by(Category categoryToUpdate) throws ResourceNotFoundException {
        if (categoryToUpdate == null || !existsCategory.byId(categoryToUpdate.getId())) {
            Integer categoryId = (categoryToUpdate != null)
                    ? categoryToUpdate.getId() : null;
            throw new ResourceNotFoundException(Category.class.getSimpleName(), categoryId);
        }
        return categoryRepository.saveAndFlush(categoryToUpdate);
    }
}
