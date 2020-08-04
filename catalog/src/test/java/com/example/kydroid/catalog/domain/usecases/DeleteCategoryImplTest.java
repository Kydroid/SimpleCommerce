package com.example.kydroid.catalog.domain.usecases;

import com.example.kydroid.catalog.domain.entities.exceptions.ResourceNotFoundException;
import com.example.kydroid.catalog.domain.ports.input.ExistsCategory;
import com.example.kydroid.catalog.domain.ports.output.CategoryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DeleteCategoryImplTest {

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private ExistsCategory existsCategory;

    @InjectMocks
    private DeleteCategoryImpl deleteCategoryImpl;

    @Test
    void deleteCategory_whenCategoryIdValid() throws ResourceNotFoundException {
        when(existsCategory.byId(anyInt()))
                .thenReturn(true);
        deleteCategoryImpl.byId(1);
    }

    @Test
    void throwResourceNotFoundException_whenCategoryIdNotFound() throws ResourceNotFoundException {
        when(existsCategory.byId(anyInt()))
                .thenReturn(false);

        assertThrows(ResourceNotFoundException.class,
                () -> deleteCategoryImpl.byId(0));
    }

}