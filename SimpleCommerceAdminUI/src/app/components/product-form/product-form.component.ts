import {Component, OnInit} from '@angular/core';
import {Product} from '../../entities/product';
import {ProductService} from '../../services/product.service';
import {Currency} from '../../entities/currency.enum';
import {ActivatedRoute, Router} from '@angular/router';
import {ToastsService} from '../../services/toasts.service';

@Component({
  selector: 'app-product-form',
  templateUrl: './product-form.component.html',
  styleUrls: ['./product-form.component.css']
})
export class ProductFormComponent implements OnInit {
  private _product: Product;
  currencies: string[] = Object.values(Currency);

  constructor(private _productService: ProductService, private toastsService: ToastsService,
              private route: ActivatedRoute, private router: Router) {
  }

  ngOnInit(): void {
    this.resetProduct();
    const productId = this.route.snapshot.params.id;
    if (productId) {
      this.getProductById(productId);
    }
  }

  private resetProduct(): void {
    this._product = {id: undefined, title: undefined, ref: undefined, money: {currency: Currency.eur}};
  }

  resetForm(): void {
    this.resetProduct();
  }

  private getProductById(productId: number): void {
    this._productService.getProductById(productId)
      .subscribe(
        productPersisted => {
          this._product = productPersisted;
        },
        error => {
          this.router.navigate(['product']);
          this.toastsService.addToast({type: 'error', message: error.error.message});
        }
      );
  }

  onSubmitProductForm(): void {
    if (this._product.id) {
      this.updateProduct();
    } else {
      this.addProduct();
    }
  }

  addProduct(): void {
    this._productService.addProduct(this._product)
      .subscribe(
        productCreated => {
          this._product = productCreated;
          this.toastsService.addToast({type: 'success', message: 'Product added'});
        },
        error => {
          this.toastsService.addToast({type: 'error', message: error.error.message});
        }
      );
  }

  updateProduct(): void {
    this._productService.updateProduct(this._product)
      .subscribe(productUpdated => {
          this._product = productUpdated;
          this.toastsService.addToast({type: 'success', message: 'Product updated'});
        },
        error => {
          this.toastsService.addToast({type: 'error', message: error.error.message});
        }
      );
  }

  get product(): Product {
    return this._product;
  }
}
