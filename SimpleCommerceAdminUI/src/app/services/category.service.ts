import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Category} from '../entities/category';

@Injectable({
  providedIn: 'root'
})
export class CategoryService {

  static readonly headers = new HttpHeaders({'Content-Type': 'application/json'});

  private urlCategories = 'http://localhost:8080/api/v1.0/categories';

  constructor(private http: HttpClient) {
  }

  getAllCategories(): Observable<Array<Category>> {
    return this.http.get<Array<Category>>(this.urlCategories, {headers: CategoryService.headers});
  }

  getCategoryById(categoryId: number): Observable<Category> {
    return this.http.get<Category>(this.urlCategories + '/' + categoryId, {headers: CategoryService.headers});
  }

  addCategory(category: Category): Observable<Category> {
    return this.http.post<Category>(this.urlCategories, category, {headers: CategoryService.headers});
  }

  updateCategory(category: Category): Observable<Category> {
    return this.http.put<Category>(this.urlCategories + '/' + category.id, category, {headers: CategoryService.headers});
  }

  deleteCategoryById(categoryId: number): Observable<any> {
    return this.http.delete(this.urlCategories + '/' + categoryId, {
      observe: 'response',
      headers: CategoryService.headers
    });
  }

}
