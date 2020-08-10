package com.example.kydroid.catalog.domain.entities.product;

import javax.persistence.Embeddable;
import java.math.BigDecimal;

@Embeddable
class Money {

    private BigDecimal amount;
    private Currency currency;

    public Money() {
    }

    public Money(BigDecimal amount, Currency currency) {
        this.amount = amount;
        this.currency = currency;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public Currency getCurrency() {
        return currency;
    }
}
