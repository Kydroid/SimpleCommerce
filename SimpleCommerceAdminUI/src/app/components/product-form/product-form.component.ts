import {Component, OnInit} from '@angular/core';
import {Product} from '../../entities/product';
import {ProductService} from '../../services/product.service';
import {Currency} from '../../entities/currency.enum';
import {ActivatedRoute, Router} from '@angular/router';
import {ToastsService} from '../../services/toasts.service';
import {Category} from '../../entities/category';
import {CategoryService} from '../../services/category.service';

@Component({
  selector: 'app-product-form',
  templateUrl: './product-form.component.html',
  styleUrls: ['./product-form.component.css']
})
export class ProductFormComponent implements OnInit {
  private _product: Product;
  private _categories: Category[];
  currencies: string[] = Object.values(Currency);

  constructor(private productService: ProductService, private categoryService: CategoryService, private toastsService: ToastsService,
              private route: ActivatedRoute, private router: Router) {
  }

  ngOnInit(): void {
    this.loadCategories();
    this.resetProduct();
    const productId = this.route.snapshot.params.id;
    if (productId) {
      this.getProductById(productId);
    }
  }

  private resetProduct(): void {
    this._product = {id: undefined, title: undefined, ref: undefined, money: {currency: Currency.eur}, category: null};
  }

  resetForm(): void {
    this.resetProduct();
    this.router.navigate(['/product']);
  }

  loadCategories(): void {
    this.categoryService.getAllCategories()
      .subscribe(categoriesFounded => {
          this._categories = categoriesFounded;
        },
        error => {
          this.toastsService.addToast({type: 'error', message: error.error.message});
        }
      );
  }

  private getProductById(productId: number): void {
    this.productService.getProductById(productId)
      .subscribe(
        productPersisted => {
          this._product = productPersisted;
        },
        error => {
          this.router.navigate(['/product']);
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
    this.productService.addProduct(this._product)
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
    this.productService.updateProduct(this._product)
      .subscribe(productUpdated => {
          this._product = productUpdated;
          this.toastsService.addToast({type: 'success', message: 'Product updated'});
        },
        error => {
          this.toastsService.addToast({type: 'error', message: error.error.message});
        }
      );
  }

  compareCategorySelect(categoryA: Category, categoryB: Category): boolean {
    return categoryA && categoryB ? categoryA.id === categoryB.id : categoryA === categoryB;
  }

  get product(): Product {
    return this._product;
  }

  get categories(): Category[] {
    return this._categories;
  }
}
