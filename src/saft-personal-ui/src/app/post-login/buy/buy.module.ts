import { IonicModule } from '@ionic/angular';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BuyPageRoutingModule } from './buy-routing.module';
import { BuyPage } from './buy.page';
import { SharedModule } from '../../shared/shared.module';
import { MatButtonModule } from '@angular/material/button';
import { MatStepperModule } from '@angular/material/stepper';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';

@NgModule({
  imports: [
    IonicModule,
    CommonModule,
    FormsModule,
    BuyPageRoutingModule,
    MatButtonModule,
    SharedModule,
    MatStepperModule,
    MatFormFieldModule,
    MatInputModule,
    ReactiveFormsModule,
  ],
  declarations: [BuyPage],
})
export class BuyModule {}
