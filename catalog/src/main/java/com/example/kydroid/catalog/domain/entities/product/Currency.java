package com.example.kydroid.catalog.domain.entities.product;

enum Currency {
    EUR("EUR");

    private String currencyCode;

    Currency(String currencyCode) {
        this.currencyCode = currencyCode;
    }
}
