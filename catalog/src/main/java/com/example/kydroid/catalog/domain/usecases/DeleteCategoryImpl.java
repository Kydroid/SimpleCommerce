package com.example.kydroid.catalog.domain.usecases;

import com.example.kydroid.catalog.domain.entities.category.Category;
import com.example.kydroid.catalog.domain.entities.exceptions.ResourceNotFoundException;
import com.example.kydroid.catalog.domain.ports.input.DeleteCategory;
import com.example.kydroid.catalog.domain.ports.input.ExistsCategory;
import com.example.kydroid.catalog.domain.ports.output.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeleteCategoryImpl implements DeleteCategory {

    private final CategoryRepository categoryRepository;
    private final ExistsCategory existsCategory;

    @Autowired
    public DeleteCategoryImpl(CategoryRepository categoryRepository, ExistsCategory existsCategory) {
        this.categoryRepository = categoryRepository;
        this.existsCategory = existsCategory;
    }

    public void byId(Integer id) throws ResourceNotFoundException {
        if (!existsCategory.byId(id)) {
            throw new ResourceNotFoundException(Category.class.getSimpleName(), id);
        }
        categoryRepository.deleteById(id);
    }
}
