package com.example.kydroid.catalog.domain.usecases;

import com.example.kydroid.catalog.domain.ports.output.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ExistsProductImplTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ExistsProductImpl existsProductImpl;

    @Test
    void returnTrue_WhenProductExistsById() {
        when(productRepository.existsById(anyInt()))
                .thenReturn(true);

        Boolean existProduct = existsProductImpl.byId(1);
        assertTrue(existProduct);
    }

    @Test
    void returnFalse_WhenProductDoesNotExistsById() {
        when(productRepository.existsById(anyInt()))
                .thenReturn(false);

        Boolean existProduct = existsProductImpl.byId(0);
        assertFalse(existProduct);
    }

}
