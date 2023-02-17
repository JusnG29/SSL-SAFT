import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { BalancePage } from './balance.page';

const routes: Routes = [
  {
    path: '',
    component: BalancePage,
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class BalanceRoutingModule {}
