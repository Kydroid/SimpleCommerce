import {Component, OnInit} from '@angular/core';
import {Product} from '../../entities/product';
import {ProductService} from '../../services/product.service';
import {Currency} from '../../entities/currency.enum';

@Component({
  selector: 'app-product-form',
  templateUrl: './product-form.component.html',
  styleUrls: ['./product-form.component.css']
})
export class ProductFormComponent implements OnInit {
  private _product: Product;
  currencies: string[] = Object.values(Currency);

  constructor(private _productService: ProductService) {
  }

  ngOnInit(): void {
    this.resetProduct();
  }

  private resetProduct(): void {
    this._product = {id: undefined, title: undefined, ref: undefined, money: {currency: Currency.eur}};
  }

  resetForm(): void {
    this.resetProduct();
  }

  onSubmitProductForm(): void {
    this.addProduct();
  }

  addProduct(): void {
    this._productService.addProduct(this._product)
      .subscribe(
        productPersisted => {
          this._product = productPersisted;
        }
      );
  }

  get product(): Product {
    return this._product;
  }
}
