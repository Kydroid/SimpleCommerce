package com.example.kydroid.catalog.domain.usecases;

import com.example.kydroid.catalog.domain.entities.category.Category;
import com.example.kydroid.catalog.domain.entities.exceptions.ResourceNotFoundException;
import com.example.kydroid.catalog.domain.ports.input.ExistsCategory;
import com.example.kydroid.catalog.domain.ports.output.CategoryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
class UpdateCategoryImplTest {

    @Mock
    private CategoryRepository categoryRepository;
    @Mock
    private ExistsCategory existsCategory;
    @InjectMocks
    private UpdateCategoryImpl updateCategoryImpl;

    @Test
    void returnCategoryUpdated_whenCategoryValid() throws ResourceNotFoundException {
        Category categoryToUpdate = new Category("cat1");
        categoryToUpdate.setId(1);

        when(existsCategory.byId(any(Integer.class))).thenReturn(true);
        when(categoryRepository.saveAndFlush(any(Category.class)))
                .thenAnswer(invocationOnMock -> invocationOnMock.getArgument(0));

        Category categoryUpdated = updateCategoryImpl.by(categoryToUpdate);
        assertNotNull(categoryUpdated);
        assertEquals(categoryToUpdate.getId(), categoryUpdated.getId());
    }

    @Test
    void throwResourceNotFoundException_whenCategoryInvalid() throws ResourceNotFoundException {
        assertThrows(ResourceNotFoundException.class,
                () -> updateCategoryImpl.by(null));
    }

}
