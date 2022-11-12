import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CheckoutPage } from './buy/checkout/checkout.page';
import { PostLoginPage } from './post-login.page';

const routes: Routes = [
  {
    path: '',
    component: PostLoginPage,
    children: [
      {
        path: 'buy',
        loadChildren: () => import('./buy/buy.module').then((m) => m.BuyModule),
      },
      {
        path: 'history',
        loadChildren: () =>
          import('./history/history.module').then((m) => m.HistoryModule),
      },
      {
        path: '',
        redirectTo: '/home/buy',
        pathMatch: 'full',
      },
    ],
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
})
export class PostLoginRoutingModule {}
