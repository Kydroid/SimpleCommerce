package com.example.kydroid.catalog.infra.adapters;

import com.example.kydroid.catalog.domain.entities.category.Category;
import com.example.kydroid.catalog.domain.ports.output.CategoryRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class CategoryControllerTest {

    String categoriesUrl = "/categories";

    @Autowired
    private MockMvc mvc;

    @MockBean
    private CategoryRepository categoryRepository;

    @Autowired
    private ObjectMapper mapperJson;

    @Test
    void returnCategoryById_whenFindCategoryByIdValid() throws Exception {
        Category category1 = new Category("cat1");
        category1.setId(1);

        when(categoryRepository.findById(1))
                .thenReturn(java.util.Optional.of(category1));

        mvc.perform(get(categoriesUrl + "/1"))
                .andExpect(status().isOk())
                .andExpect(content().string(mapperJson.writeValueAsString(category1)))
                .andReturn();
    }

    @Test
    void throwResourceNotFoundException_whenFindCategoryByIdInvalid() throws Exception {
        mvc.perform(get(categoriesUrl + "/1"))
                .andExpect(status().isNotFound())
                .andReturn();
    }
}
