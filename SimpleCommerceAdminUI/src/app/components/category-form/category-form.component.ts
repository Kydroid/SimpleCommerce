import {Component, OnInit} from '@angular/core';
import {Category} from '../../entities/category';
import {CategoryService} from '../../services/category.service';
import {ToastsService} from '../../services/toasts.service';
import {ActivatedRoute, Router} from '@angular/router';

@Component({
  selector: 'app-category-form',
  templateUrl: './category-form.component.html',
  styleUrls: ['./category-form.component.css']
})
export class CategoryFormComponent implements OnInit {
  private _category: Category;

  constructor(private categoryService: CategoryService, private toastsService: ToastsService,
              private route: ActivatedRoute, private router: Router) {
  }

  ngOnInit(): void {
    this.resetCategory();
    const categoryId = this.route.snapshot.params.id;
    if (categoryId) {
      this.getCategoryById(categoryId);
    }
  }

  resetForm(): void {
    this.resetCategory();
    this.router.navigate(['category']);
  }

  resetCategory(): void {
    this._category = {id: undefined, title: undefined, description: undefined};
  }

  private getCategoryById(categoryId: number): void {
    this.categoryService.getCategoryById(categoryId)
      .subscribe(categoryPersisted => {
          this._category = categoryPersisted;
        },
        error => {
          this.router.navigate(['category']);
          this.toastsService.addToast({type: 'error', message: error.error.message});
        }
      );
  }

  onSubmitCategoryForm(): void {
    if (this._category.id) {
      this.updateCategory();
    } else {
      this.addCategory();
    }
  }

  addCategory(): void {
    this.categoryService.addCategory(this._category)
      .subscribe(
        categoryCreated => {
          this._category = categoryCreated;
          this.toastsService.addToast({type: 'success', message: 'Category added'});
        },
        error => {
          this.toastsService.addToast({type: 'error', message: error.error.message});
        }
      );
  }

  updateCategory(): void {
    this.categoryService.updateCategory(this._category)
      .subscribe(categoryUpdated => {
          this._category = categoryUpdated;
          this.toastsService.addToast({type: 'success', message: 'Category updated'});
        },
        error => {
          console.log(error);
          this.toastsService.addToast({type: 'error', message: error.error.message});
        }
      );
  }

  get category(): Category {
    return this._category;
  }
}
