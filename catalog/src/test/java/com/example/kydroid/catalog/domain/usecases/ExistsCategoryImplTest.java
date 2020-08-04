package com.example.kydroid.catalog.domain.usecases;

import com.example.kydroid.catalog.domain.ports.output.CategoryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ExistsCategoryImplTest {

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private ExistsCategoryImpl existsCategoryImpl;

    @Test
    void returnTrue_whenCategoryExistbyId() {
        when(categoryRepository.existsById(1))
                .thenReturn(true);

        boolean existCategory = existsCategoryImpl.byId(1);
        assertTrue(existCategory);
    }

    @Test
    void returnFalse_whenCategoryDoesNotExistbyId() {
        when(categoryRepository.existsById(0))
                .thenReturn(false);

        boolean existCategory = existsCategoryImpl.byId(0);
        assertFalse(existCategory);
    }
}
