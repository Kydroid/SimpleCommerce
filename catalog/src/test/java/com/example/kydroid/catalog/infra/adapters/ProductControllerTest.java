package com.example.kydroid.catalog.infra.adapters;

import com.example.kydroid.catalog.domain.entities.product.Product;
import com.example.kydroid.catalog.domain.ports.output.ProductRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class ProductControllerTest {

    String productUrl = "/products";

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ProductRepository productRepository;

    @Autowired
    private ObjectMapper mapperJson;

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
    void returnProductById_whenFindProductByIdValid() throws Exception {
        when(productRepository.findById(1))
                .thenReturn(java.util.Optional.ofNullable(product1));

        mvc.perform(get(productUrl + "/1"))
                .andExpect(status().isOk())
                .andExpect(content().string(mapperJson.writeValueAsString(product1)))
                .andReturn();
    }

    @Test
    void throwResourceNotFoundException_whenFindProductByIdInvalid() throws Exception {
        mvc.perform(get(productUrl + "/0"))
                .andExpect(status().isNotFound())
                .andReturn();
    }

}
