import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {HttpClientModule} from '@angular/common/http';

import {AppComponent} from './app.component';
import {AppRoutingModule} from './app-routing.module';
import {NavbarComponent} from './components/navbar/navbar.component';
import {ProductListComponent} from './components/product-list/product-list.component';
import {FormsModule} from '@angular/forms';
import {ProductFormComponent} from './components/product-form/product-form.component';
import {ConfirmDialogComponent} from './components/confirm-dialog/confirm-dialog.component';
import {ToastsComponent} from './components/toasts/toasts.component';

@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    ProductListComponent,
    ProductFormComponent,
    ConfirmDialogComponent,
    ToastsComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
