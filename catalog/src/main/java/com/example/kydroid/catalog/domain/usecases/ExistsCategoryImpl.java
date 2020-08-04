package com.example.kydroid.catalog.domain.usecases;

import com.example.kydroid.catalog.domain.ports.input.ExistsCategory;
import com.example.kydroid.catalog.domain.ports.output.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExistsCategoryImpl implements ExistsCategory {

    private final CategoryRepository categoryRepository;

    @Autowired
    public ExistsCategoryImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public boolean byId(Integer id) {
        return categoryRepository.existsById(id);
    }
}
