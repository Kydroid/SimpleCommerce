import {Money} from './money';

export interface Product {
  id: number;
  ref: string;
  title: string;
  description?: string;
  stockQuantity?: number;
  money?: Money;
}

