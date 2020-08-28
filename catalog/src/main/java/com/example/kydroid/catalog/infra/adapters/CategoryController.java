package com.example.kydroid.catalog.infra.adapters;

import com.example.kydroid.catalog.domain.entities.category.Category;
import com.example.kydroid.catalog.domain.entities.exceptions.ResourceCreationConflictException;
import com.example.kydroid.catalog.domain.entities.exceptions.ResourceCreationFailedException;
import com.example.kydroid.catalog.domain.entities.exceptions.ResourceNotFoundException;
import com.example.kydroid.catalog.domain.ports.input.CreateCategory;
import com.example.kydroid.catalog.domain.ports.input.DeleteCategory;
import com.example.kydroid.catalog.domain.ports.input.FindCategory;
import com.example.kydroid.catalog.domain.ports.input.UpdateCategory;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Api(tags = "API Categories v1.0")
@CrossOrigin
@RestController
@RequestMapping("categories")
public class CategoryController {

    private final CreateCategory createCategory;
    private final FindCategory findCategory;
    private final UpdateCategory updateCategory;
    private final DeleteCategory deleteCategory;

    @Autowired
    public CategoryController(CreateCategory createCategory, FindCategory findCategory, UpdateCategory updateCategory,
                              DeleteCategory deleteCategory) {
        this.createCategory = createCategory;
        this.findCategory = findCategory;
        this.updateCategory = updateCategory;
        this.deleteCategory = deleteCategory;
    }

    @GetMapping
    public ResponseEntity<List<Category>> getAllCategories() {
        List<Category> categoriesFounded = findCategory.all();
        return ResponseEntity.ok(categoriesFounded);
    }

    @GetMapping("{categoryId}")
    public ResponseEntity<Category> getCategory(@PathVariable("categoryId") Integer categoryId)
            throws ResourceNotFoundException {
        Category categoryFounded = findCategory.byId(categoryId);
        return ResponseEntity.ok(categoryFounded);
    }

    @PostMapping
    public ResponseEntity<Category> addCategory(@RequestBody Category categoryToAdd)
            throws ResourceCreationConflictException, ResourceCreationFailedException {
        Category categoryAdded = createCategory.add(categoryToAdd);

        URI locationCategoryAdded = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(categoryAdded.getId())
                .toUri();

        return ResponseEntity.created(locationCategoryAdded).body(categoryAdded);
    }

    @PutMapping("{categoryId}")
    public ResponseEntity<Category> updateCategory(@PathVariable("categoryId") Integer categoryId,
                                                   @RequestBody Category categoryToUpdate)
            throws ResourceNotFoundException {
        categoryToUpdate.setId(categoryId);
        Category categoryUpdated = updateCategory.by(categoryToUpdate);
        return ResponseEntity.ok(categoryUpdated);
    }

    @DeleteMapping("{categoryId}")
    public ResponseEntity deleteCategory(@PathVariable("categoryId") Integer categoryId)
            throws ResourceNotFoundException {
        deleteCategory.byId(categoryId);
        return ResponseEntity.noContent().build();
    }
}
