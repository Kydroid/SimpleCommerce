import {Money} from './money';
import {Category} from './category';

export interface Product {
  id: number;
  ref: string;
  title: string;
  description?: string;
  stockQuantity?: number;
  money?: Money;
  category?: Category;
}

