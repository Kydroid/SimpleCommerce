package com.example.kydroid.catalog.domain.usecases;

import com.example.kydroid.catalog.domain.entities.exceptions.ResourceNotFoundException;
import com.example.kydroid.catalog.domain.ports.input.ExistsCategory;
import com.example.kydroid.catalog.domain.ports.input.UpdateProduct;
import com.example.kydroid.catalog.domain.ports.output.CategoryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
class DeleteCategoryImplTest {

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private UpdateProduct updateProduct;

    @Mock
    private ExistsCategory existsCategory;

    @InjectMocks
    private DeleteCategoryImpl deleteCategoryImpl;

    @Test
    void deleteCategory_whenCategoryIdValid() throws ResourceNotFoundException {
        Integer categoryId = 1;

        when(existsCategory.byId(anyInt()))
                .thenReturn(true);

        deleteCategoryImpl.byId(categoryId);
        verify(updateProduct, times(1)).removeCategoryRelationshipByCategoryId(categoryId);
        verify(categoryRepository, times(1)).deleteById(categoryId);
    }

    @Test
    void throwResourceNotFoundException_whenCategoryIdNotFound() throws ResourceNotFoundException {
        when(existsCategory.byId(anyInt()))
                .thenReturn(false);

        assertThrows(ResourceNotFoundException.class,
                () -> deleteCategoryImpl.byId(0));
    }

}
