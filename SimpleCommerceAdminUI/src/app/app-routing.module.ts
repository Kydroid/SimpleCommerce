import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {ProductListComponent} from './components/product-list/product-list.component';
import {ProductFormComponent} from './components/product-form/product-form.component';

const routes: Routes = [
  {path: 'product', component: ProductFormComponent},
  {path: 'product/:id', component: ProductFormComponent},
  {path: 'products', component: ProductListComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
  declarations: []
})
export class AppRoutingModule {
}
