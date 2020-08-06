package com.example.kydroid.catalog.infra.adapters;

import com.example.kydroid.catalog.domain.entities.category.Category;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CategoryControllerE2ETest {

    String categoriesUrl = "/categories";

    @Autowired
    private ObjectMapper mapperJson;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void returnCategory_whenCreateCategoryValid() {
        Category category1 = new Category("cat1");

        HttpEntity<Category> categoryHttpEntity = new HttpEntity<>(category1);
        ResponseEntity<Category> categoryResponseEntity = restTemplate.postForEntity(categoriesUrl, categoryHttpEntity, Category.class);

        assertEquals(HttpStatus.CREATED, categoryResponseEntity.getStatusCode());
        Category categoryCreated = categoryResponseEntity.getBody();
        assertNotNull(categoryCreated);
        assertNotNull(categoryCreated.getId());
        assertEquals(category1.getTitle(), categoryCreated.getTitle());
    }

    @Test
    void throwResourceCreationConflictException_whenCreateCategoryInvalid() {
        Category category1 = new Category("cat1");
        category1.setId(1);

        HttpEntity<Category> categoryHttpEntity = new HttpEntity<>(category1);
        ResponseEntity<Category> categoryResponseEntity = restTemplate.postForEntity(categoriesUrl, categoryHttpEntity, Category.class);
        assertEquals(HttpStatus.CONFLICT, categoryResponseEntity.getStatusCode());
    }
}
