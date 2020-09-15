package com.example.kydroid.catalog.infra.configuration;

import com.example.kydroid.catalog.domain.entities.category.Category;
import com.example.kydroid.catalog.domain.entities.exceptions.ResourceCreationConflictException;
import com.example.kydroid.catalog.domain.entities.exceptions.ResourceCreationFailedException;
import com.example.kydroid.catalog.domain.entities.product.Product;
import com.example.kydroid.catalog.domain.ports.input.CreateCategory;
import com.example.kydroid.catalog.domain.ports.input.CreateProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@Profile("!test")
@Component
public class AppStartupRunner implements ApplicationRunner {

    private final CreateCategory createCategory;
    private final CreateProduct createProduct;

    @Autowired
    public AppStartupRunner(CreateCategory createCategory, CreateProduct createProduct) {
        this.createCategory = createCategory;
        this.createProduct = createProduct;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        this.addDataDemo();
    }

    private void addDataDemo() {
        Category category1 = new Category("cat1");
        category1.setDescription("cat1 desc");
        Category category2 = new Category("cat2");
        Category category3 = new Category("cat3");
        List<Category> categories = Arrays.asList(category1, category2, category3);

        Product product1 = new Product("ref1", "prod1", "prod1 desc", 10,
                new BigDecimal(50), "EUR", category1);
        Product product2 = new Product("ref2", "prod2", "prod2 desc", 20,
                new BigDecimal(20), "USD", category2);
        Product product3 = new Product("ref3", "prod3");
        product3.setCategory(category3);
        Product product4 = new Product("ref4", "prod4");
        product4.setCategory(category3);
        Product product5 = new Product("ref5", "prod5");
        product5.setCategory(category3);
        List<Product> products = Arrays.asList(product1, product2, product3, product4, product5);


        categories.forEach(category -> {
            try {
                createCategory.add(category);
            } catch (ResourceCreationConflictException resourceCreationConflictException) {
                resourceCreationConflictException.printStackTrace();
            } catch (ResourceCreationFailedException resourceCreationFailedException) {
                resourceCreationFailedException.printStackTrace();
            }
        });

        products.forEach(product -> {
            try {
                createProduct.add(product);
            } catch (ResourceCreationFailedException resourceCreationFailedException) {
                resourceCreationFailedException.printStackTrace();
            } catch (ResourceCreationConflictException resourceCreationConflictException) {
                resourceCreationConflictException.printStackTrace();
            }
        });
    }
}
