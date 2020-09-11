export enum Currency {
    EUR = '\u20AC',
    USD = '\u0024'
}

export class CurrencySymbol {
    static get(currencyKey: string): string {
        return Currency[currencyKey as keyof typeof Currency];
    }
}
