import { IonicModule } from '@ionic/angular';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BuyPageRoutingModule } from './buy-routing.module';
import { BuyPage } from './buy.page';
import { SharedModule } from '../../shared/shared.module';
import { MatButtonModule } from '@angular/material/button';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { ProductListItemComponent } from './product-list-item/product-list-item.component';
import { ProductCounterComponent } from './product-counter/product-counter.component';

@NgModule({
  imports: [
    IonicModule,
    CommonModule,
    FormsModule,
    BuyPageRoutingModule,
    MatButtonModule,
    SharedModule,
    MatFormFieldModule,
    MatInputModule,
    ReactiveFormsModule,
  ],
  declarations: [BuyPage, ProductListItemComponent, ProductCounterComponent],
})
export class BuyModule {}
