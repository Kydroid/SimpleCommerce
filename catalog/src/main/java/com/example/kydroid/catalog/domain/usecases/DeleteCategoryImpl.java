package com.example.kydroid.catalog.domain.usecases;

import com.example.kydroid.catalog.domain.entities.category.Category;
import com.example.kydroid.catalog.domain.entities.exceptions.ResourceNotFoundException;
import com.example.kydroid.catalog.domain.ports.input.DeleteCategory;
import com.example.kydroid.catalog.domain.ports.input.ExistsCategory;
import com.example.kydroid.catalog.domain.ports.input.UpdateProduct;
import com.example.kydroid.catalog.domain.ports.output.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
class DeleteCategoryImpl implements DeleteCategory {

    private final CategoryRepository categoryRepository;
    private final ExistsCategory existsCategory;
    private final UpdateProduct updateProduct;

    @Autowired
    public DeleteCategoryImpl(CategoryRepository categoryRepository, ExistsCategory existsCategory,
                              UpdateProduct updateProduct) {
        this.categoryRepository = categoryRepository;
        this.existsCategory = existsCategory;
        this.updateProduct = updateProduct;
    }

    @Transactional
    @Override
    public void byId(Integer categoryId) throws ResourceNotFoundException {
        if (!existsCategory.byId(categoryId)) {
            throw new ResourceNotFoundException(Category.class.getSimpleName(), categoryId);
        }
        updateProduct.removeCategoryRelationshipByCategoryId(categoryId);
        categoryRepository.deleteById(categoryId);
    }
}
