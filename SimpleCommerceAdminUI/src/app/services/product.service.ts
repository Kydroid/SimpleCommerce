import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders, HttpParams, HttpResponse} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Product} from '../entities/product';


@Injectable({
  providedIn: 'root'
})
export class ProductService {

  static readonly headers = new HttpHeaders({'Content-Type': 'application/json'});

  private urlProducts = 'http://localhost:8080/api/v1.0/products';

  constructor(private http: HttpClient) {
  }

  private getHttpParamsWithPagination(page: number, pageSize: number): HttpParams {
    const pageNumberStartingWithZero = page - 1;
    const params = new HttpParams()
      .set('page', pageNumberStartingWithZero.toString())
      .set('pagesize', pageSize.toString());
    return params;
  }

  getAllProductsPaginate(page: number, pageSize: number): Observable<HttpResponse<Array<Product>>> {
    const httpParams = this.getHttpParamsWithPagination(page, pageSize);

    return this.http.get<Array<Product>>(this.urlProducts, {
      params: httpParams,
      observe: 'response',
      headers: ProductService.headers
    });
  }

  getAllProductsByTitlePaginate(page: number, pageSize: number, searchProductsKeywords: string): Observable<HttpResponse<Array<Product>>> {
    const httpParams = this.getHttpParamsWithPagination(page, pageSize)
      .set('title', searchProductsKeywords);

    return this.http.get<Array<Product>>(this.urlProducts, {
      params: httpParams,
      observe: 'response',
      headers: ProductService.headers
    });
  }
}
