package com.example.kydroid.catalog.domain.entities.category;

import com.example.kydroid.catalog.domain.entities.common.BaseEntity;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;

@Entity
public class Category extends BaseEntity {

    @NotBlank(message = "Title category is mandatory !")
    private String title;
    private String description;

    public Category() {
    }

    public Category(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
