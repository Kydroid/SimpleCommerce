import React from "react";
import {Money} from "../../entities/money";
import {CurrencySymbol} from "../../entities/currency.enum";

export class MoneyFormatted extends React.Component<any, any> {
    productPriceFormatter(): string {
        let productPriceFormatted = "";
        const money: Money = this.props.money;
        if (money && money.currency && money.amount) {
            productPriceFormatted = CurrencySymbol.get(money.currency) + money.amount;
        }
        return productPriceFormatted;
    }

    render() {
        return (
            <span>{this.productPriceFormatter()}</span>
        );
    }
}
