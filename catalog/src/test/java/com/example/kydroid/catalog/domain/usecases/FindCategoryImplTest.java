package com.example.kydroid.catalog.domain.usecases;

import com.example.kydroid.catalog.domain.entities.category.Category;
import com.example.kydroid.catalog.domain.entities.exceptions.ResourceNotFoundException;
import com.example.kydroid.catalog.domain.ports.output.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FindCategoryImplTest {

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private FindCategoryImpl findCategoryImpl;

    private List<Category> categories;

    @BeforeEach
    void setup() {
        Category category1 = new Category("cat1");
        category1.setId(1);
        Category category2 = new Category("cat2");
        category2.setId(2);

        categories = new ArrayList<>();
        categories.add(category1);
        categories.add(category2);
    }

    @Test
    void returnAllCategories_whenFindAllCategories() {
        when(categoryRepository.findAll()).thenReturn(categories);

        List<Category> categoriesFounded = findCategoryImpl.all();
        assertNotNull(categoriesFounded);
        assertEquals(categories.size(), categoriesFounded.size());
    }


    @Test
    void returnEmptyList_whenFindAllCategoriesNoResult() {
        when(categoryRepository.findAll()).thenReturn(new ArrayList<>());

        List<Category> categoriesFounded = findCategoryImpl.all();
        assertNotNull(categoriesFounded);
        assertTrue(categoriesFounded.isEmpty());
    }

    @Test
    void returnCategoryById_whenFindCategoryByIdValid() throws ResourceNotFoundException {
        Integer categoryIdToFound = categories.get(0).getId();
        when(categoryRepository.findById(categoryIdToFound))
                .thenReturn(java.util.Optional.ofNullable(categories.get(0)));

        Category categoryFounded = findCategoryImpl.byId(categoryIdToFound);
        assertNotNull(categoryFounded);
        assertEquals(categoryIdToFound, categoryFounded.getId());
    }

    @Test
    void throwResourceNotFoundException_whenFindCategoryByIdInvalid() throws ResourceNotFoundException {
        when(categoryRepository.findById(anyInt())).thenReturn(java.util.Optional.ofNullable(null));

        assertThrows(ResourceNotFoundException.class,
                () -> findCategoryImpl.byId(0));
    }

}
