package com.example.kydroid.catalog.domain.usecases;

import com.example.kydroid.catalog.domain.entities.exceptions.ResourceNotFoundException;
import com.example.kydroid.catalog.domain.ports.input.ExistsProduct;
import com.example.kydroid.catalog.domain.ports.output.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
class DeleteProductImplTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private ExistsProduct existsProduct;

    @InjectMocks
    private DeleteProductImpl deleteProductImpl;

    @Test
    void deleteProduct_whenProductIdValid() throws ResourceNotFoundException {
        when(existsProduct.byId(anyInt()))
                .thenReturn(true);

        deleteProductImpl.byId(1);
    }

    @Test
    void throwResourceNotFoundException_whenProductIdNotFound() {
        when(existsProduct.byId(anyInt()))
                .thenReturn(false);

        assertThrows(ResourceNotFoundException.class,
                () -> deleteProductImpl.byId(0));
    }
}
