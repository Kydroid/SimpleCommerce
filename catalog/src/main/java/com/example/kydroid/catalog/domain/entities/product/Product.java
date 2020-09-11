package com.example.kydroid.catalog.domain.entities.product;

import com.example.kydroid.catalog.domain.entities.category.Category;
import com.example.kydroid.catalog.domain.entities.common.BaseEntity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

@Entity
public class Product extends BaseEntity {

    @Column(unique = true)
    @NotBlank(message = "Reference product is mandatory !")
    private String ref;
    @NotBlank(message = "Title product is mandatory !")
    private String title;
    private String description;
    private Integer stockQuantity;
    @Embedded
    private Money money;
    @ManyToOne(fetch = FetchType.LAZY)
    private Category category;

    public Product() {
    }

    public Product(String ref, String title) {
        this.ref = ref;
        this.title = title;
    }

    public Product(String ref, String title, String description, Integer stockQuantity,
                   BigDecimal amount, String currency, Category category) {
        this.ref = ref;
        this.title = title;
        this.description = description;
        this.stockQuantity = stockQuantity;
        this.money = new Money(amount, Currency.valueOf(currency));
        this.category = category;
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

    public Money getMoney() {
        return money;
    }

    public void setMoney(Money money) {
        this.money = money;
    }

    public Integer getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(Integer stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
