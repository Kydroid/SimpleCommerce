import {Currency} from './currency.enum';

export interface Money {
  amount?: number;
  currency?: Currency;
}
