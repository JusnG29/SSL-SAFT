import { IonicModule } from '@ionic/angular';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { BuyPageRoutingModule } from './buy-routing.module';
import { BuyPage } from './buy.page';

@NgModule({
  imports: [IonicModule, CommonModule, FormsModule, BuyPageRoutingModule],
  declarations: [BuyPage],
})
export class BuyModule {}
