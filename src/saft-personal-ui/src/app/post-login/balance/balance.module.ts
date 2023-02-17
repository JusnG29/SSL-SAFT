import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { BalanceRoutingModule } from './balance-routing.module';
import { SharedModule } from '../../shared/shared.module';
import { IonicModule } from '@ionic/angular';
import { BalancePage } from './balance.page';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';

@NgModule({
  declarations: [BalancePage],
  imports: [
    CommonModule,
    FontAwesomeModule,
    BalanceRoutingModule,
    IonicModule,
    SharedModule,
  ],
})
export class BalanceModule {}
