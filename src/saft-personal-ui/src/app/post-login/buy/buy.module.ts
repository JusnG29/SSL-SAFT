import { IonicModule } from '@ionic/angular';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { BuyPageRoutingModule } from './buy-routing.module';
import { BuyPage } from './buy.page';
import { SharedModule } from '../../shared/shared.module';
import { MatButtonModule } from '@angular/material/button';

@NgModule({
  imports: [
    IonicModule,
    CommonModule,
    FormsModule,
    BuyPageRoutingModule,
    MatButtonModule,
    SharedModule,
  ],
  declarations: [BuyPage],
})
export class BuyModule {}
