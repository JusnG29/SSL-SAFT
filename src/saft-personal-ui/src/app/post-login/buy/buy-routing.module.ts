import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { BuyPage } from './buy.page';
import { CheckoutPage } from './checkout/checkout.page';

const routes: Routes = [
  {
    path: '',
    component: BuyPage,
  },
  {
    path: 'checkout',
    component: CheckoutPage,
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class BuyPageRoutingModule {}
