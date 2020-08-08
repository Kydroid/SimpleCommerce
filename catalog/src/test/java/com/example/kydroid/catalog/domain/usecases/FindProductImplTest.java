package com.example.kydroid.catalog.domain.usecases;

import com.example.kydroid.catalog.domain.entities.exceptions.ResourceNotFoundException;
import com.example.kydroid.catalog.domain.entities.product.Product;
import com.example.kydroid.catalog.domain.ports.output.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FindProductImplTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    FindProductImpl findProductImpl;

    private List<Product> products;
    private Product product1;
    private Product product2;
    private Product product3;

    @BeforeEach
    void setup() {
        product1 = new Product("ref1", "product1");
        product1.setId(1);
        product2 = new Product("ref2", "product2");
        product2.setId(2);
        product3 = new Product("ref3", "product3");
        product3.setId(3);
        products = Arrays.asList(product1, product2, product3);
    }

    @Test
    void returnAllProducts_whenFindAllProducts() {
        Integer numberOfProducts = products.size();

        when(productRepository.findAll())
                .thenReturn(products);

        List<Product> productsFounded = findProductImpl.all();
        assertNotNull(productsFounded);
        assertEquals(numberOfProducts, productsFounded.size());
    }

    @Test
    void returnEmptyList_whenFindAllProductsNoResult() {
        when(productRepository.findAll())
                .thenReturn(new ArrayList<>());

        List<Product> productsFounded = findProductImpl.all();
        assertNotNull(productsFounded);
        assertTrue(productsFounded.isEmpty());
    }

    @Test
    void returnProductById_whenFindProductByIdValid() throws ResourceNotFoundException {
        Integer productIdToFind = product1.getId();

        when(productRepository.findById(productIdToFind))
                .thenReturn(java.util.Optional.ofNullable(product1));

        Product productFounded = findProductImpl.byId(productIdToFind);
        assertNotNull(productFounded);
        assertEquals(productIdToFind, productFounded.getId());
    }

    @Test
    void throwResourceNotFoundException_whenFindProductByIdInvalid() {
        when(productRepository.findById(anyInt()))
                .thenReturn(Optional.ofNullable(null));

        assertThrows(ResourceNotFoundException.class,
                () -> findProductImpl.byId(0));
    }
}
