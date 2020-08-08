package com.example.kydroid.catalog.domain.usecases;

import com.example.kydroid.catalog.domain.entities.exceptions.ResourceCreationConflictException;
import com.example.kydroid.catalog.domain.entities.exceptions.ResourceCreationFailedException;
import com.example.kydroid.catalog.domain.entities.product.Product;
import com.example.kydroid.catalog.domain.ports.output.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreateProductImplTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private CreateProductImpl createProductImpl;

    @Test
    void returnProductCreated_whenProductValid() throws ResourceCreationFailedException, ResourceCreationConflictException {
        Product productToAdd = new Product("ref1", "product1");

        when(productRepository.saveAndFlush(productToAdd))
                .thenAnswer(invocationOnMock ->
                {
                    Product productPersisted = invocationOnMock.getArgument(0);
                    productPersisted.setId(1);
                    return productPersisted;
                });

        Product productAdded = createProductImpl.add(productToAdd);
        assertNotNull(productAdded);
        assertEquals(1, productAdded.getId());
    }

    @Test
    void throwResourceCreationFailedException_whenProductEmpty() {
        Product productToAdd = new Product();

        assertThrows(ResourceCreationFailedException.class,
                () -> createProductImpl.add(productToAdd));
    }

    @Test
    void throwResourceCreationConflictException_whenProductAlreadyPersisted() {
        Product productToAdd = new Product();
        productToAdd.setId(1);

        assertThrows(ResourceCreationConflictException.class,
                () -> createProductImpl.add(productToAdd));
    }

}
