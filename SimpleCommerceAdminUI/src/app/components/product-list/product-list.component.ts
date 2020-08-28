import {Component, ElementRef, OnDestroy, OnInit, ViewChild} from '@angular/core';
import {Product} from '../../entities/product';
import {ProductService} from '../../services/product.service';
import {ConfirmDialogService} from '../../services/confirm-dialog.service';
import {Subscription} from 'rxjs';
import {ToastsService} from '../../services/toasts.service';

@Component({
  selector: 'app-product-list',
  templateUrl: './product-list.component.html',
  styleUrls: ['./product-list.component.css']
})
export class ProductListComponent implements OnInit, OnDestroy {
  static readonly PAGE_START = 1;
  private _products: Array<Product> = [];
  private _productsTotalCount = 0;

  private _currentPage = ProductListComponent.PAGE_START;
  private _pageSize = 10;
  private _numberOfPages = ProductListComponent.PAGE_START;

  private confirmDialogSubscription: Subscription;
  @ViewChild('searchProductsInput') searchProductsInput: ElementRef;

  constructor(private _productService: ProductService, private confirmDialogService: ConfirmDialogService,
              private toastsService: ToastsService) {
  }

  ngOnInit(): void {
    this.loadAllProductsPaginate();
  }

  ngOnDestroy(): void {
    if (this.confirmDialogSubscription) {
      this.confirmDialogSubscription.unsubscribe();
    }
  }

  private loadProducts(): void {
    const searchProductsKeywords = this.searchProductsInput.nativeElement.value;
    if (searchProductsKeywords) {
      this.searchProductsByTitlePaginate(searchProductsKeywords);
    } else {
      this.loadAllProductsPaginate();
    }
  }

  private loadAllProductsPaginate(): void {
    this._productService.getAllProductsPaginate(this.currentPage, this.pageSize)
      .subscribe(responseProductsFounded => {
          this._products = responseProductsFounded.body;
          this._productsTotalCount = Number(responseProductsFounded.headers.get('X-Total-Count')) || this._productsTotalCount;
          this.paginateProductList();
        },
        error => {
          this.toastsService.addToast({type: 'error', message: error.error.message});
        }
      );
  }

  private searchProductsByTitlePaginate(searchProductsKeywords: string): void {
    this._productService.getAllProductsByTitlePaginate(this.currentPage, this.pageSize, searchProductsKeywords)
      .subscribe(responseProductsFounded => {
          this._products = responseProductsFounded.body;
          if (responseProductsFounded.headers.get('X-Total-Count')) {
            this._productsTotalCount = Number(responseProductsFounded.headers.get('X-Total-Count'));
          }
          this.paginateProductList();
        },
        error => {
          this.toastsService.addToast({type: 'error', message: error.error.message});
        }
      );
  }

  onSearchProductsChange(): void {
    this._currentPage = ProductListComponent.PAGE_START;
    const searchProductsKeywords = this.searchProductsInput.nativeElement.value;
    this.searchProductsByTitlePaginate(searchProductsKeywords);
  }

  confirmDeleteProduct(productToDelete: Product): void {
    const messageConfirmDeleteProduct = `Are you sure to delete this product "${productToDelete.title}" ?`;
    this.confirmDialogSubscription = this.confirmDialogService.show('Confirmation', messageConfirmDeleteProduct)
      .subscribe(resultConfirmDeleteProduct => {
          if (resultConfirmDeleteProduct) {
            this.deleteProduct(productToDelete);
          }
        },
        error => {
          this.toastsService.addToast({type: 'error', message: error.error.message});
        }
      );
  }

  deleteProduct(productToDelete: Product): void {
    this._productService.deleteProductById(productToDelete.id)
      .subscribe(responseProductToDelete => {
          if (responseProductToDelete.status === 204) {
            this._products = this.products.filter(product => product.id !== productToDelete.id);
            this.toastsService.addToast({type: 'success', message: 'Product deleted'});
          }
        },
        error => {
          this.toastsService.addToast({type: 'error', message: error.error.message});
        }
      );
  }

  changePage(pageNumber: number): void {
    this._currentPage = pageNumber;
    this.loadProducts();
  }

  previousPage(): void {
    this._currentPage--;
    this.loadProducts();
  }

  nextPage(): void {
    this._currentPage++;
    this.loadProducts();
  }

  changePageSize(): void {
    this._currentPage = ProductListComponent.PAGE_START;
    this.loadProducts();
  }

  private paginateProductList(): void {
    this._numberOfPages = Math.ceil(this.productsTotalCount / this.pageSize);
  }

  getPageRange(): Array<number> {
    return Array.from(Array(this._numberOfPages), (_, i) => i + 1);
  }

  getShowingProductsStart(): number {
    if (this._productsTotalCount === 0) {
      return this._productsTotalCount;
    }
    const showingProductsStart = (this._currentPage - 1) * this._pageSize + 1;
    return showingProductsStart;
  }

  getShowingProductsEnd(): number {
    let showingProductsEnd = this._currentPage * this._pageSize;
    showingProductsEnd = (showingProductsEnd > this._productsTotalCount) ? this._productsTotalCount : showingProductsEnd;
    return showingProductsEnd;
  }

  get products(): Array<Product> {
    return this._products;
  }

  get pageSize(): number {
    return this._pageSize;
  }

  set pageSize(value: number) {
    this._pageSize = value;
  }

  get currentPage(): number {
    return this._currentPage;
  }

  get productsTotalCount(): number {
    return this._productsTotalCount;
  }

  get numberOfPages(): number {
    return this._numberOfPages;
  }
}
