package com.example.kydroid.catalog.domain.usecases;

import com.example.kydroid.catalog.domain.entities.category.Category;
import com.example.kydroid.catalog.domain.entities.exceptions.ResourceNotFoundException;
import com.example.kydroid.catalog.domain.entities.product.Product;
import com.example.kydroid.catalog.domain.ports.input.ExistsProduct;
import com.example.kydroid.catalog.domain.ports.output.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UpdateProductImplTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private ExistsProduct existsProduct;

    @InjectMocks
    private UpdateProductImpl updateProductImpl;

    @Test
    void returnProductUpdated_whenProductValid() throws ResourceNotFoundException {
        Product productToUpdate = new Product("ref1", "product1");
        productToUpdate.setId(1);

        when(existsProduct.byId(anyInt()))
                .thenReturn(true);
        when(productRepository.saveAndFlush(any(Product.class)))
                .thenAnswer(invocationOnMock -> invocationOnMock.getArgument(0));

        Product productUpdated = updateProductImpl.by(productToUpdate);
        assertNotNull(productUpdated);
        assertNotNull(productUpdated.getId());
    }

    @Test
    void throwResourceNotFoundException_whenProductInvalid() {
        assertThrows(ResourceNotFoundException.class,
                () -> updateProductImpl.by(null));
    }

    @Test
    void removeCategoryRelationshipProducts_whenProductsWithThisCategoryExists() {
        Category category = new Category("cat1");
        category.setId(1);

        updateProductImpl.removeCategoryRelationshipByCategoryId(category.getId());
        verify(productRepository, times(1)).updateProductsSetCategoryNullByCategoryId(category.getId());
    }
}
