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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FindProductImplTest {

    @InjectMocks
    FindProductImpl findProductImpl;
    @Mock
    private ProductRepository productRepository;
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
    void returnAllProductsPaginate_whenFindAllProductsPageable() {
        int page = 1;
        int pageSize = 10;
        Page<Product> pagedProducts = new PageImpl(products);

        Pageable pageable = PageRequest.of(page, pageSize);
        when(productRepository.findAll(pageable))
                .thenReturn(pagedProducts);

        List<Product> productsFounded = findProductImpl.all(page, pageSize);
        assertNotNull(productsFounded);
        assertEquals(3, productsFounded.size());
        verify(productRepository, times(1)).findAll(pageable);
    }

    @Test
    void throwIllegalArgumentException_whenFindAllProductsPageableWithBadArgumentPageSize() {
        int page = 1;
        int pageSize = 0;

        assertThrows(IllegalArgumentException.class, () -> {
            findProductImpl.all(page, pageSize);
        });
    }

    @Test
    void returnTotalNumberOfProducts_whenCountAllProducts() {
        findProductImpl.allCount();
        verify(productRepository, times(1)).count();
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

    @Test
    void returnProductsByTitlePaginate_whenFindProductsByTitlePageable() {
        int page = 1;
        int pageSize = 10;
        String titleKeyword = "product1";

        Pageable pageable = PageRequest.of(page, pageSize);
        when(productRepository.findByTitleContainingIgnoreCase(titleKeyword, pageable))
                .thenReturn(Arrays.asList(product1));

        List<Product> productsFounded = findProductImpl.byTitleContainingIgnoreCase(page, pageSize, titleKeyword);
        assertNotNull(productsFounded);
        assertEquals(1, productsFounded.size());
        verify(productRepository, times(1)).findByTitleContainingIgnoreCase(titleKeyword, pageable);
    }

    @Test
    void throwIllegalArgumentException_whenFindProductsByTitlePageableWithBadArgumentPageSize() {
        int page = 1;
        int pageSize = 1000000;
        String titleKeyword = "product1";

        assertThrows(IllegalArgumentException.class, () -> {
            findProductImpl.byTitleContainingIgnoreCase(page, pageSize, titleKeyword);
        });
    }

    @Test
    void returnTotalNumberOfProductsByTitle_whenCountProductsByTitleContainingIgnoreCase() {
        String titleKeyword = "product1";
        findProductImpl.byTitleContainingIgnoreCaseCount(titleKeyword);
        verify(productRepository, times(1)).countByTitleContainingIgnoreCase(titleKeyword);
    }

    @Test
    void returnLastUpdatedProducts_whenFindLastUpdatedProducts() {
        int limit = 6;
        findProductImpl.lastUpdatedLimitedTo(limit);
        Pageable pageable = PageRequest.of(0, limit);
        verify(productRepository, times(1)).findAllByOrderByLastModifiedDateDesc(pageable);
    }

    @Test
    void throwIllegalArgumentException_whenFindProductsByCategoryWithBadArgumentLimit() {
        int limit = 1000000;

        assertThrows(IllegalArgumentException.class, () -> {
            findProductImpl.lastUpdatedLimitedTo(limit);
        });
    }

    @Test
    void returnProductsByCategory_whenFindProductsByCategoryIdValid() {
        findProductImpl.byCategoryId(1);
        verify(productRepository, times(1)).findAllByCategoryId(1);
    }
}
