import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {ProductListComponent} from './components/product-list/product-list.component';
import {ProductFormComponent} from './components/product-form/product-form.component';
import {CategoryListComponent} from './components/category-list/category-list.component';
import {CategoryFormComponent} from './components/category-form/category-form.component';

const routes: Routes = [
  {path: 'product', component: ProductFormComponent},
  {path: 'product/:id', component: ProductFormComponent},
  {path: 'products', component: ProductListComponent},
  {path: 'category', component: CategoryFormComponent},
  {path: 'category/:id', component: CategoryFormComponent},
  {path: 'categories', component: CategoryListComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
  declarations: []
})
export class AppRoutingModule {
}
