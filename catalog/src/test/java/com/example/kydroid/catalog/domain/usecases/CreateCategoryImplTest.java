package com.example.kydroid.catalog.domain.usecases;

import com.example.kydroid.catalog.domain.entities.category.Category;
import com.example.kydroid.catalog.domain.entities.exceptions.ResourceCreationConflictException;
import com.example.kydroid.catalog.domain.entities.exceptions.ResourceCreationFailedException;
import com.example.kydroid.catalog.domain.ports.output.CategoryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreateCategoryImplTest {
    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CreateCategoryImpl createCategoryImpl;

    @Test
    void returnCategoryCreated_whenNewCategoryValid() throws ResourceCreationConflictException, ResourceCreationFailedException {
        Category categoryTodAdd = new Category("category1");

        when(categoryRepository.saveAndFlush(any(Category.class)))
                .thenAnswer(invocationOnMock -> {
                    Category categoryPersisted = invocationOnMock.getArgument(0);
                    categoryPersisted.setId(1);
                    return categoryPersisted;
                });

        Category categoryAdded = createCategoryImpl.add(categoryTodAdd);
        assertNotNull(categoryAdded);
        assertEquals(1, categoryAdded.getId());
    }

    @Test
    void throwResourceCreationFailedException_whenCategoryEmpty() {
        Category categoryTodAdd = new Category();
        assertThrows(ResourceCreationFailedException.class,
                () -> createCategoryImpl.add(categoryTodAdd));
    }

    @Test
    void throwResourceCreationConflictException_whenCategoryAlreadyExist() {
        Category categoryTodAdd = new Category("test");
        categoryTodAdd.setId(1);
        assertThrows(ResourceCreationConflictException.class,
                () -> createCategoryImpl.add(categoryTodAdd));
    }

}
