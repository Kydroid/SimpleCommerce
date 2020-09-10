import {Component, OnDestroy, OnInit} from '@angular/core';
import {Category} from '../../entities/category';
import {CategoryService} from '../../services/category.service';
import {ToastsService} from '../../services/toasts.service';
import {Subscription} from 'rxjs';
import {ConfirmDialogService} from '../../services/confirm-dialog.service';

@Component({
  selector: 'app-category-list',
  templateUrl: './category-list.component.html',
  styleUrls: ['./category-list.component.css']
})
export class CategoryListComponent implements OnInit, OnDestroy {
  private _categories: Array<Category> = [];

  private confirmDialogSubscription: Subscription;

  constructor(private categoryService: CategoryService, private confirmDialogService: ConfirmDialogService,
              private toastsService: ToastsService) {
  }

  ngOnInit(): void {
    this.loadCategories();
  }

  ngOnDestroy(): void {
    if (this.confirmDialogSubscription) {
      this.confirmDialogSubscription.unsubscribe();
    }
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

  confirmDeleteCategory(categoryToDelete: Category): void {
    const messageConfirmDeleteCategory = `Are you sure to delete this category "${categoryToDelete.title}" ?
    All products related to this category will be released.`;
    this.confirmDialogSubscription = this.confirmDialogService.show('Confirmation', messageConfirmDeleteCategory)
      .subscribe(resultConfirmDeleteCategory => {
          if (resultConfirmDeleteCategory) {
            this.deleteCategory(categoryToDelete);
          }
        },
        error => {
          this.toastsService.addToast({type: 'error', message: error.error.message});
        }
      );
  }

  private deleteCategory(categoryToDelete: Category): void {
    this.categoryService.deleteCategoryById(categoryToDelete.id)
      .subscribe(responseCategoryToDelete => {
          if (responseCategoryToDelete.status === 204) {
            this._categories = this._categories.filter(category => category.id !== categoryToDelete.id);
            this.toastsService.addToast({
                type: 'success',
                message: 'Category deleted and all products related to this category have been released.'
              }
            );
          }
        },
        error => {
          this.toastsService.addToast({type: 'error', message: error.error.message});
        }
      );
  }

  get categories(): Array<Category> {
    return this._categories;
  }
}
