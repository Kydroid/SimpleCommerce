package com.example.kydroid.catalog.domain.entities.product;

import com.example.kydroid.catalog.domain.entities.common.BaseEntity;

import javax.persistence.Embedded;
import javax.validation.constraints.NotBlank;

public class Product extends BaseEntity {

    @NotBlank(message = "Reference product is mandatory !")
    private String ref;
    @NotBlank(message = "Title product is mandatory !")
    private String title;
    private String description;
    private Integer stockQuantity;
    @Embedded
    private Price price;

    public Product(String ref, String title) {
        this.ref = ref;
        this.title = title;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
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

    public Price getPrice() {
        return price;
    }

    public void setPrice(Price price) {
        this.price = price;
    }

    public Integer getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(Integer stockQuantity) {
        this.stockQuantity = stockQuantity;
    }
}
