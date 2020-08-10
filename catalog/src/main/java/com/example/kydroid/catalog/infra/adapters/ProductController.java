package com.example.kydroid.catalog.infra.adapters;

import com.example.kydroid.catalog.domain.entities.exceptions.ResourceCreationConflictException;
import com.example.kydroid.catalog.domain.entities.exceptions.ResourceCreationFailedException;
import com.example.kydroid.catalog.domain.entities.exceptions.ResourceNotFoundException;
import com.example.kydroid.catalog.domain.entities.product.Product;
import com.example.kydroid.catalog.domain.ports.input.CreateProduct;
import com.example.kydroid.catalog.domain.ports.input.DeleteProduct;
import com.example.kydroid.catalog.domain.ports.input.FindProduct;
import com.example.kydroid.catalog.domain.ports.input.UpdateProduct;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@Api(tags = "API Product v1.0")
@CrossOrigin
@RestController
@RequestMapping("products")
public class ProductController {

    private final CreateProduct createProduct;
    private final DeleteProduct deleteProduct;
    private final FindProduct findProduct;
    private final UpdateProduct updateProduct;

    @Autowired
    public ProductController(CreateProduct createProduct, DeleteProduct deleteProduct, FindProduct findProduct, UpdateProduct updateProduct) {
        this.createProduct = createProduct;
        this.deleteProduct = deleteProduct;
        this.findProduct = findProduct;
        this.updateProduct = updateProduct;
    }

    @GetMapping("{productId}")
    public ResponseEntity<Product> getProduct(@PathVariable("productId") Integer productId) throws ResourceNotFoundException {
        Product productFounded = findProduct.byId(productId);
        return ResponseEntity.ok(productFounded);
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product productToCreate) throws ResourceCreationConflictException, ResourceCreationFailedException {
        Product productAdded = createProduct.add(productToCreate);

        URI locationProductAdded = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("api/v1.0/products/{id}")
                .buildAndExpand(productAdded.getId())
                .toUri();

        return ResponseEntity.created(locationProductAdded).body(productAdded);
    }

    @PutMapping("{productId}")
    public ResponseEntity<Product> updateProduct(@PathVariable("productId") Integer productId,
                                                 @RequestBody Product productToUpdate) throws ResourceNotFoundException {
        Product productUpdated = updateProduct.by(productToUpdate);
        return ResponseEntity.ok(productUpdated);
    }

    @DeleteMapping("{productId}")
    public ResponseEntity deleteProduct(@PathVariable("productId") Integer productId) throws ResourceNotFoundException {
        deleteProduct.byId(productId);
        return ResponseEntity.noContent().build();
    }

}