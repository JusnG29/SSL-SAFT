import { IonicModule } from '@ionic/angular';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { HistoryPage } from './history.page';

import { HistoryRoutingModule } from './history-routing.module';
import { MatCardModule } from '@angular/material/card';
import { SharedModule } from '../../shared/shared.module';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';

@NgModule({
  imports: [
    IonicModule,
    CommonModule,
    FontAwesomeModule,
    FormsModule,
    HistoryRoutingModule,
    MatCardModule,
    SharedModule,
  ],
  declarations: [HistoryPage],
})
export class HistoryModule {}
